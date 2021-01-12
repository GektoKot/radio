import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'js/api/resource.js'
import router from 'js/router/router.js'
import App from 'js/pages/App.vue'
import store from 'js/store/store.js'
import { connect } from './util/ws.js'
import 'vuetify/dist/vuetify.min.css'

if (profile) {
    connect()
}

Vue.use(Vuetify)


const opts = {
    theme: {
        light: true
    }
}

new Vue({
    el: '#app',
    vuetify: new Vuetify(opts),
    store,
    router,
    // iconfont: 'mdiSvg', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg',
    render: a => a(App)
})




