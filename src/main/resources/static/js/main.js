import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import App from 'pages/App.vue'
import { connect } from './util/ws.js'
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
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
    // iconfont: 'mdiSvg', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg',
    render: a => a(App)
})




