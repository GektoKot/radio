<template>
<!--  <v-app>-->
<!--    <v-navigation-drawer app>-->
<!--      &lt;!&ndash; &ndash;&gt;-->
<!--    </v-navigation-drawer>-->

<!--    <v-app-bar app>-->
<!--      &lt;!&ndash; &ndash;&gt;-->
<!--    </v-app-bar>-->

<!--    &lt;!&ndash; Sizes your content based upon application components &ndash;&gt;-->
<!--    <v-main>-->

<!--      &lt;!&ndash; Provides the application the proper gutter &ndash;&gt;-->
<!--      <v-container fluid>-->

<!--        &lt;!&ndash; If using vue-router &ndash;&gt;-->
<!--        <router-view></router-view>-->
<!--      </v-container>-->
<!--    </v-main>-->

<!--    <v-footer app>-->
<!--      &lt;!&ndash; &ndash;&gt;-->
<!--    </v-footer>-->
<!--  </v-app>-->
  <div class="style">
    <div v-if="!profile">Необходимо авторизоваться через
      <a href="/login">Google</a>
    </div>
    <div v-else>
      <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
      <messages-list :messages="messages" />
    </div>
  </div>
</template>

<script>
import MessagesList from "components/messages/MessagesList.vue"
import { addHandler} from "util/ws";
import { getIndex } from "util/connections";

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
.style{
  color: red;
}
</style>