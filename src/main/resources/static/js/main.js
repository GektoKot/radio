import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource.js'
import App from 'pages/App.vue'
import store from 'store/store.js'
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
    store,
    // iconfont: 'mdiSvg', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg',
    render: a => a(App)
})




