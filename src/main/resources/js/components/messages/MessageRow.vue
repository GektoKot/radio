<template>
  <v-card class="mb-2"
          color="light-blue lighten-5"
          elevation="15"
          outlined
          shaped>
      <!--<v-avatar v-if="message.author && message.author.userpic"
                class="ml-3"
                size="48">
        <img
            :src="message.author.userpic"
            :alt="message.author.name"
        >
      </v-avatar>
      <v-avatar v-else
                color="indigo"
                class="ml-3"
                size="48">
        <v-icon dark>
          mdi-account-circle
        </v-icon>
      </v-avatar>
    <span class="pl-1">
      {{ authorName }}
    </span>-->
    <user-link :user="message.author"></user-link>
      <v-card-text>{{ message.text }}</v-card-text>
    <media v-if="message.link" :message="message"></media>
    <v-card-actions>
      <v-btn @click="edit" small rounded>
        <v-icon class="material-icons">volume_up</v-icon>
        Retransmit
      </v-btn>
      <v-btn @click="del" small rounded>
        <v-icon class="material-icons">mic_off</v-icon>
        Shut down
      </v-btn>
    </v-card-actions>
    <v-expansion-panels focusable>
      <v-expansion-panel>
        <v-expansion-panel-header color="light-blue lighten-4">Comments</v-expansion-panel-header>
        <v-expansion-panel-content color="light-blue lighten-4">
          <comments-list :comments="message.comments" :message-id="message.id"></comments-list>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>

  </v-card>
</template>

<script>
import {mapActions} from 'vuex'
import Media from 'js/components/media/Media.vue'
import CommentsList from 'js/components/comments/CommentsList.vue'
import UserLink from 'js/components/UserLink.vue'

export default {
  props: ['message', 'editMessage'],
  components: {UserLink, CommentsList, Media},

  methods: {
    ...mapActions(['deleteMessageAction']),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.deleteMessageAction(this.message)
    }
  }
}
</script>

<style>

</style>