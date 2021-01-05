<template>
  <v-card
      class="mx-auto"
      max-width="400"
  >
    <v-flex v-if="type === 'href'"  xs12 sm6 offset-sm3>
      <v-img v-if="message.linkCover"
             :src="message.linkCover"
             height="200px"
      ></v-img>
      <v-card-title>
        <a :href="message.link">{{ message.linkTitle || message.link }}</a>
      </v-card-title>
      <v-card-subtitle v-if="message.linkDescription">
        <div>
          {{ message.linkDescription }}
        </div>
      </v-card-subtitle>
    </v-flex>

    <v-flex v-if="type === 'image'"  xs12 sm6 offset-sm3>
      <a :href="message.link">
        <v-img v-if="message.linkCover" :src="message.linkCover"></v-img>
      </a>

    </v-flex>

    <v-flex v-if="type === 'youtube'"  xs12 sm6 offset-sm3>
      <you-tube :src="message.link"></you-tube>
    </v-flex>

  </v-card>
</template>

<script>
import YouTube from 'components/media/YouTube.vue'

export default {
  name: "Media",
  components: { YouTube },
  props: ['message'],
  data() {
    return {
      type: "href"
    }
  },
  beforeMount() {
    if (this.message.link.indexOf('youtu') > -1) {
      this.type = 'youtube'
    } else if (this.message.link.match('\.(jpeg|jpg|gif|png)$') !== null) {
      this.type = 'image'
    } else {
      this.type = 'href'
    }
  }
}
</script>

<style scoped>

</style>