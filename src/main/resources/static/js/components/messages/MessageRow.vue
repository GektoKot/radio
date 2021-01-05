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
  </v-card>
</template>

<script>
import Media from "/media/Media.vue";
import { mapActions } from 'vuex'
export default {
  props: ['message', 'editMessage'],
  components: { Media },
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