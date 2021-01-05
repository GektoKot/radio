<template>
  <v-card
      class="mx-auto"
      max-width="344"
  >
    <v-flex v-if="type === 'href'">
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

    <v-flex v-if="type === 'image'">
      <a :href="message.link">
        <v-img v-if="message.linkCover" :src="message.linkCover"></v-img>
        {{message.link}}
      </a>

    </v-flex>

    <v-flex v-if="type === 'youtube'">
      <you-tube :src="message.link"></you-tube>
    </v-flex>

  </v-card>
</template>

<script>
import YouTube from "./YouTube"

export default {
  name: "Media",
  components: {YouTube},
  props: ['message'],
  data() {
    return {
      type: "href"
    }
  },
  beforeMount() {
    if (this.message.link.indexOf('youtube') > -1) {
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