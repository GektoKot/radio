<template>
  <v-card class="mb-2"
          color="light-blue lighten-5"
      elevation="15"
      outlined
      shaped>
    (<i>{{ message.id }}</i>)
    <v-card-text>{{ message.text }}</v-card-text>
    <media v-if="message.link" :message="message"></media>
    <v-card-actions>
<!--        <input type="button" value="Renovation" @click="edit"/>-->
      <v-btn @click="edit" small rounded>
        <v-icon class="material-icons">volume_up</v-icon>
        Retransmit
      </v-btn>
<!--      <input type="button" value="Extinguish" @click="del"/>-->
      <v-btn @click="del" small rounded>
        <v-icon class="material-icons">mic_off</v-icon>
        Shut down
      </v-btn>
    </v-card-actions>
    <comments-list :comments="message.comments" :message-id="message.id"></comments-list>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex'
import Media from 'components/media/Media.vue'
import CommentsList from 'components/comments/CommentsList.vue'

export default {
  props: ['message', 'editMessage'],
  components: { CommentsList, Media },
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