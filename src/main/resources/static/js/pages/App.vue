<template>
  <div>
    <div v-if="!profile">
      <a href="/login">Enter</a> to the RaDiO station!
    </div>
    <div v-else>
      <div><b>{{ profile.name }}</b> <a href="/logout">Exit</a> from RaDiO station!</div>
      <messages-list :messages="messages"/>
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

</style>