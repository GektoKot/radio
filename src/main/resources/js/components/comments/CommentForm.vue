<template>
  <v-layout row class="mx-6">

      <v-text-field
          label="Add comment"
          Comment
          v-model="text"
          @keyup.enter="save"
      />
      <v-btn
          class="ml-2"
          color="orange lighten-4"
          fab
          @click="save">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>


  </v-layout>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  name: "CommentForm",
  props: ['messageId'],
  data() {
    return{
      text: ''
    }
  },
  methods: {
    ...mapActions(['createCommentAction']),
    async save() {
      await this.createCommentAction({
        text: this.text,
        message: {
          id: this.messageId
        }
      })
      this.text = ''
    }
  }
}
</script>

<style scoped>

</style>