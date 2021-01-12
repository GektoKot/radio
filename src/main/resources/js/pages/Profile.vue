<template>
  <v-card
      class="mx-auto mt-3"
      color="blue lighten-5"
      elevation="15"
      max-width="600"
      outlined
      shaped
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline">
          Name: <br/>
          -{{ profile.name }}
        </div>
        <div v-if="isMyProfile"
             class="overline">
          E-mail: <br/>
          -{{ profile.email }}
        </div>
        <div class="overline" v-if="profile.local">
          Local: <br/>
          -{{ profile.local }}
        </div>
        <div class="overline" v-if="profile.gender">
          Gender: <br/>
          -{{ profile.gender }}
        </div>

        <div class="overline">
          Subscriptions: {{ profile.subscriptions && profile.subscriptions.length }}
        </div>

        <router-link v-if="isMyProfile"
                     :to="`/subscriptions/${profile.id}`">
          <div class="overline">
            Subscribers: {{ profile.subscribers && profile.subscribers.length }}
          </div>
        </router-link>
        <div v-else
             class="overline">
          Subscribers: {{ profile.subscribers && profile.subscribers.length }}
        </div>

        <v-list-item-subtitle>
          The last broadcast: <br/>
          {{ profile.lastVisit }}
        </v-list-item-subtitle>
        <v-btn v-if="!isMyProfile"
               @click="changeSubscription"
               rounded>
          {{ isISubscribed ? "unsubscribe" : "subscribe" }}
        </v-btn>

      </v-list-item-content>


      <v-avatar size="80"
                color="teal">
        <v-img :src="profile.userpic" alt="User"/>

      </v-avatar>

    </v-list-item>
  </v-card>
</template>

<script>
import ProfileApi from 'js/api/profile.js'

export default {
  name: 'profile',
  data() {
    return {
      profile: {}
    }
  },
  computed: {
    isMyProfile() {
      return !this.$route.params.id || this.$route.params.id === this.$store.state.profile.id
    },
    isISubscribed() {
      return this.profile.subscribers && this.profile.subscribers.find(subscription => {
        return subscription.subscriber === this.$store.state.profile.id
      })
    }
  },
  watch: {
    '$route'() {
      this.updateProfile()
    }
  },
  methods: {
    async changeSubscription() {
      const data = await ProfileApi.changeSubscription(this.profile.id)
      this.profile = await data.json()
    },
    async updateProfile() {
      const id = this.$route.params.id || this.$store.state.profile.id
      const data = await ProfileApi.get(id)
      this.profile = await data.json()
      this.$forceUpdate()
    }
  },
  beforeMount() {
    this.updateProfile()
  }
}
</script>

<style>

</style>