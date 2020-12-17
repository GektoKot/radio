<template>
  <div>
    (<i>{{ message.id }}</i>) <b>{{ message.text }}</b>
    <span style="position: absolute; right: 0">
        <input type="button" value="Renovation" @click="edit"/>
        <input type="button" value="Extinguish" @click="del"/>
        </span>
  </div>
</template>

<script>
export default {
  props: ['message', 'editMessage', 'deleteMessage', 'messages'],
  methods: {
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.deleteMessage(this.message)
    }
  }
}
</script>

<style>

</style>