<template>
  <v-list three-line
          color="light-blue lighten-4">
<!--    <v-list-item-title>Comments:</v-list-item-title>-->
    <template v-for="(item, index) in comments">
      <v-divider
          v-if="index > 0"
          :key="index"
      ></v-divider>
      <comment-item
          :comment="item"
          :key="`item` + index"
      ></comment-item>
    </template>
    <comment-form :message-id="messageId"></comment-form>
  </v-list>
</template>

<script>
import CommentItem from 'components/comments/CommentItem.vue'
import CommentForm from 'components/comments/CommentForm.vue'
export default {
  name: "CommentsList",
  components: {CommentForm, CommentItem},
  props: ['comments', 'messageId']
}
</script>

<style scoped>

</style>