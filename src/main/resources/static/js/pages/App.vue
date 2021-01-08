<template>

  <v-app>
    <div>
      <v-app-bar color="light-blue lighten-5" dense rounded>
        <v-toolbar-title>RadioRest
          <v-icon class="material-icons">radio</v-icon>
        </v-toolbar-title>
        <v-btn v-if="profile"
               class="ml-2"
               rounded
               small
               :disabled="$route.path === '/'"
               @click="showMessages">
          Transmissions
        </v-btn>
        <v-btn v-if="profile"
               class="mx-1"
               rounded
               small
               :disabled="$route.path === '/user'"
               @click="showProfile">
          Newscaster
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn v-if="!profile" href="/login">
          <v-icon>mdi-arrow-up-bold-box-outline</v-icon>
          Log In
        </v-btn>
        <span v-if="profile">{{ profile.name }}</span>
        <v-btn class="ml-2"
               small
               rounded
               v-if="profile" href="/logout">
          <v-icon>mdi-arrow-down-bold-box-outline</v-icon>
          Log Out
        </v-btn>
      </v-app-bar>
    </div>


    <v-main>
      <!--<v-container v-if="profile">
        <messages-list/>
      </v-container>-->
      <!--<v-container v-if="!profile">
        <h1>LogIn for Broadcasting!!!</h1>
      </v-container>-->
      <router-view></router-view>
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
import {mapState, mapMutations} from 'vuex'
// import MessagesList from 'components/messages/MessagesList.vue'
import {addHandler} from 'util/ws'
// import router from 'static/js/router/router.js'


export default {
  /* components: {
     MessagesList
   },*/
  computed: mapState(['profile']),
  methods: {
    ...mapMutations(['deleteMessageMutation',
      'updateMessageMutation',
      'createMessageMutation',
      'createCommentMutation']),
    showMessages() {
      this.$router.push('/')

    },
    showProfile() {
      this.$router.push('/user')
    }
  },
  created() {
    addHandler(data => {
      if (data.objectType === 'MESSAGE') {
        // const index = this.messages.findIndex(item => item.id === data.body.id)
        switch (data.eventType) {
          case 'CREATE':
            this.createMessageMutation(data.body)
            break
          case 'UPDATE':
            this.updateMessageMutation(data.body)
            break
          case 'DELETE':
            this.deleteMessageMutation(data.body)
            break
          default:
            console.error('EventType is unknown ')
        }
      } else if (data.objectType === 'COMMENT') {
        // const index = this.messages.findIndex(item => item.id === data.body.id)
        switch (data.eventType) {
          case 'CREATE':
            this.createCommentMutation(data.body)
            break
          default:
            console.error('EventType is unknown ')
        }
      } else {
        console.error('EventType or ObjectType is unknown :' + data.objectType)
      }
    })
  },
  beforeMount() {
    if (!this.profile) {
      this.$router.replace('/auth')
    }
  }
}
</script>

<style>
</style>