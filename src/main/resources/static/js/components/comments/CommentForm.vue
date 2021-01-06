<template>
  <v-layout row>

      <v-text-field
          label="Add comment"
          Comment
          v-model="text"
          outlined
          @keyup.enter="save"
      />
      <v-btn color="orange lighten-4"  @click="save">
        Comment
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