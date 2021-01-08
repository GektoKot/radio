<template>
  <router-link :to="`/user/${user.id}`">
    <v-avatar v-if="user && user.userpic"
              class="ml-3"
              size="48">
      <img
          :src="user.userpic"
          :alt="user.name"
      >
    </v-avatar>
    <v-avatar v-else
              color="indigo"
              class="ml-3"
              size="48">
      <v-icon dark>
        mdi-account-circle
      </v-icon>
    </v-avatar>
    <span class="pl-1">
      {{ userName }}
    </span>
  </router-link>

</template>

<script>
export default {
  name: 'UserLink',
  props: ['user'],
  computed: {
    userName() {
      return this.user ? this.user.name : 'unknown'
    }
  },
}
</script>

<style scoped>

</style>