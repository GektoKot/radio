<template>
  <div>
    <input type="text" placeholder="Radio message" v-model="text"/>
    <input type="button" value="Broadcast!" @click="save"/>
  </div>
</template>

<script>
import { sendMessage } from "util/ws";



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

      sendMessage({id: this.id, text: this.text})

      this.text = ''
      this.id = ''


      /*const message = {text: this.text}

      if (this.id) {
        this.$resource('/messages{/id}').update({id: this.id}, message).then(result =>
            result.json().then(data => {
              const index = getIndex(this.messages, data.id)
              this.messages.splice(index, 1, data)
              this.text = ''
              this.id = ''
            }))
      } else {
        this.$resource('/messages{/id}').save({}, message).then(result =>
            result.json().then(data => {
              this.messages.push(data);
              this.text = ''
            })
        )
      }*/
    }
  }
}
</script>

<style>

</style>