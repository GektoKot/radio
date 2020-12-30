<template>
  <v-layout column>
<!--    <input type="text" placeholder="Radio message" v-model="text"/>-->
    <v-text-field
        label="Radio message"
        Radio message
        v-model="text"
        outlined
    ></v-text-field>
<!--    <input type="button" value="Broadcast!" @click="save"/>-->
    <v-btn color="orange lighten-4" class="mb-5" @click="save" small>
      <v-icon >mdi-antenna</v-icon>Broadcast<v-icon >mdi-antenna</v-icon>
    </v-btn>
  </v-layout>
</template>

<script>
import MessagesApi from 'api/messages.js'

export default {

  props: ['messages', 'messageAttributes'],
  data() {
    return {
      text: '',
      id: ''
    }
  },
  watch: {
    messageAttributes(newValue, oldValue) {
      this.text = newValue.text
      this.id = newValue.id
    }
  },
  methods: {
    save() {
      const message = {
        id: this.id,
        text: this.text
      }

      if (this.id) {
        MessagesApi.update(message).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === data.id)
              this.messages.splice(index, 1, data)
            }))
      } else {
        MessagesApi.create(message).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === data.id)
              if (index > -1) {
                this.messages.splice(index, 1, data)
              } else {
                this.messages.push(data)
              }
            })
        )
      }
      this.text = ''
      this.id = ''
    }
  }
}
</script>

<style>

</style>