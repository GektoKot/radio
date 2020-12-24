import Vue from 'vue'
// import Vuetify from 'vuetify/lib'
import Vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.min.css'
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
        dark: true
    }
}

new Vue({
    // el: '#app',
    vuetify: new Vuetify(opts),
    render: a => a(App)
}).$mount('#app')




