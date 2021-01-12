<template>
<span></span>
</template>

<script>
import  {mapActions} from 'vuex'
export default {
  name: 'LazyLoader',
  methods: mapActions(['loadPageAction']),
  mounted() {
    window.onscroll = () => {
      const el = document.documentElement
      const isBottomScreen = el.scrollTop + window.innerHeight === el.offsetHeight
      let isBottomOfScreen= (window.innerHeight + window.pageYOffset) >= document.body.offsetHeight - 2

      // if (isBottomScreen) {
      if (isBottomOfScreen) {
        this.loadPageAction()
      }
    }
  },
  beforeDestroy() {
    window.onscroll = null
  }
}
</script>

<style scoped>

</style>