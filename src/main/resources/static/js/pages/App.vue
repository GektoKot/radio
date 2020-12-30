<template>

  <v-app>
    <div>
      <v-app-bar color="light-blue lighten-5" dense rounded>
        <v-toolbar-title>RadioRest<v-icon class="material-icons">radio</v-icon> </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn v-if="!profile" href="/login">
          <v-icon>mdi-arrow-up-bold-box-outline</v-icon>
          Log In
        </v-btn>
        <span v-if="profile">{{ profile.name }}</span>
        <v-btn class="ml-2" v-if="profile" href="/logout">
          <v-icon>mdi-arrow-down-bold-box-outline</v-icon>
          Log Out
        </v-btn>
      </v-app-bar>
    </div>


      <v-main>
        <v-container v-if="profile">
          <messages-list :messages="messages"/>
        </v-container>
        <v-container v-else>
          <h1>LogIn for Broadcasting!!!</h1>
        </v-container>
      </v-main>


  </v-app>

  <!--<div class="style">
    <div v-if="!profile">
      <a href="/login">Enter</a> to radio station.
    </div>
    <div v-else>
      <div>{{ profile.name }}&nbsp;<a href="/logout">Abandon</a></div>
<messages-list :messages="messages"/>
    </div>
  </div>-->
</template>

<script>
import MessagesList from 'components/messages/MessagesList.vue'
import { addHandler } from 'util/ws';


export default {
  components: {
    MessagesList
  },
  data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile
    }
  },
  created() {
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        const index = this.messages.findIndex(item => item.id === data.body.id)
        switch (data.eventType) {
          case 'CREATE':
          case 'UPDATE':
            if (index > -1) {
              this.messages.splice(index, 1, data.body)
            } else {
              this.messages.push(data.body)
            }
            break
          case 'DELETE':
            this.messages.splice(index, 1)
            break
          default:
            console.error('EventType is unknown ')
        }
      } else {
        console.error('EventType or ObjectType is unknown :'+ data.eventType + data.objectType)
      }
    })
  }
}
</script>

<style>
</style>