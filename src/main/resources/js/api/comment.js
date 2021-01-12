import Vue from 'vue'

const comments = Vue.resource('/comment{/id}')

export default {
    create: comment => comments.save({}, comment)
}