<template>
  <v-card
      class="mx-auto mt-3"
      color="grey lighten-4"
      elevation="15"
      max-width="600"
      outlined
      shaped
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline">
          Name: <br/>
          -{{profile.name}}
        </div>
        <div class="overline">
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
        <v-list-item-subtitle>
          The last broadcast: <br/>
          {{ profile.lastVisit }}
        </v-list-item-subtitle>
      </v-list-item-content>

<!--      <v-list-item-avatar-->
<!--          tile-->
<!--          size="80"-->
<!--      >-->
        <v-avatar
            size="80"
            color="teal"
        >
          <v-img :src="profile.userpic" alt="User"/>
        </v-avatar>
<!--      </v-list-item-avatar>-->
    </v-list-item>
  </v-card>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'profile',
  computed: mapState(['profile'])
}
</script>

<style>

</style>