<template>

  <v-app id="inspire">
    <v-navigation-drawer
        v-model="drawer"
        app
    >
      <!--  -->
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <!--  -->
    </v-main>
  </v-app>

 <!-- <div class="style">
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
import MessagesList from "components/messages/MessagesList.vue"
import {addHandler} from "util/ws";
import {getIndex} from "util/connections";

export default {
  data: () => ({ drawer: null }),
  components: {
    MessagesList
  },
  /*data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile
    }
  },*/
  created() {
    addHandler(data => {
      let index = getIndex(this.messages, data.id)
      if (index > -1) {
        this.messages.splice(index, 1, data)
      } else {
        this.messages.push(data)
      }
    })
  }
}
</script>

<style>
.style {
  color: brown;
  font-size: xx-large;
}
</style>