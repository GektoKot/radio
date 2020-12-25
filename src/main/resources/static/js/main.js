import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import VueResource from 'vue-resource'
import { connect } from './util/ws.js'
import App from 'pages/App.vue'

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)
Vue.use(VueResource)

const opts = {
    theme: {
        light: true
    }
}

new Vue({
    el: '#app',
    vuetify: new Vuetify(opts),
    iconfont: 'mdiSvg', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg',
    render: a => a(App)
})




