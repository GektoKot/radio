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
export default {
  components: {
    MessagesList
  },
  data() {
    return {
      messages: frontendData.messages,
      profile: frontendData.profile
    }
  }
}
</script>

<style>

</style>