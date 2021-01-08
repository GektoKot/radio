<template>
      <v-list-item>
        <!--<v-list-item-avatar size="32">
          <img :src="comment.author.userpic" />
        </v-list-item-avatar>-->
        <user-link :user="comment.author"></user-link>
        <v-list-item-content>
<!--          <v-list-item-subtitle>{{comment.author.name}}</v-list-item-subtitle>-->
          <v-list-item-title>{{comment.text}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
</template>

<script>
import UserLink from 'components/UserLink.vue'
export default {
  name: 'CommentItem',
  components: {UserLink},
  props: ['comment']
}
</script>

<style scoped>

</style>