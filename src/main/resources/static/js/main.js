import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import VueResource from 'vue-resource'
import { connect } from './util/ws.js'
import App from 'pages/App.vue'


const opts = {
    theme: {
        dark: true
    }
}

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)
Vue.use(VueResource)

new Vue({
    el: '#app',
    vuetify: new Vuetify({opts}),
    render: a => a(App)
})




