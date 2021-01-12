<template>
  <v-layout column>
<!--    <input type="text" placeholder="Radio message" v-model="text"/>-->
    <v-text-field
        label="Radio message"
        Radio message
        v-model="text"
        outlined
        @keyup.enter="save"
    ></v-text-field>
<!--    <input type="button" value="Broadcast!" @click="save"/>-->
    <v-btn color="orange lighten-4" class="mb-5" @click="save"  small>
      <v-icon >mdi-antenna</v-icon>Broadcast<v-icon >mdi-antenna</v-icon>
    </v-btn>
  </v-layout>
</template>

<script>
import { mapActions } from 'vuex'

export default {

  props: ['messageAttributes'],
  data() {
    return {
      text: '',
      id: null
    }
  },
  watch: {
    messageAttributes(newValue, oldValue) {
      this.text = newValue.text
      this.id = newValue.id
    }
  },
  methods: {
    ...mapActions(['updateMessageAction', 'createMessageAction']),
    save() {
      const message = {
        id: this.id,
        text: this.text
      }
      if (this.id) {
        this.updateMessageAction(message)
      } else {
        this.createMessageAction(message)
      }
      this.text = ''
      this.id = null
    }
  }
}
</script>

<style>

</style>