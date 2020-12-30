import Vue from 'vue'

const messages = Vue.resource('/messages{/id}')

export default {
    create: message => messages.save({}, message),
    refresh: message => messages.update({id: message.id}, message),
    delete: id => messages.remove({id})
}