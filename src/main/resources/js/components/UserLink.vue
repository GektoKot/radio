<template>
  <router-link :to="`/user/${user.id}`">
    <v-avatar v-if="user && user.userpic"
              size="48">
      <img
          :src="user.userpic"
          :alt="user.name"
      >
    </v-avatar>
    <v-avatar v-else
              color="indigo"
              size="48">
      <v-icon dark>
        mdi-account-circle
      </v-icon>
    </v-avatar>
      <span class="pl-3">
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