import Vue from 'vue'
import Vuex from 'vuex'
import MessagesApi from 'api/messages.js';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        messages: frontendData.messages,
        profile: frontendData.profile
    },
    getters: {
        sortedMessages: state =>  (state.messages || []).sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        createMessageMutation(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message) {
            const index = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, index),
                message,
                ...state.messages.slice(index + 1)
            ]
        },
        deleteMessageMutation(state, message) {
            const index = state.messages.findIndex(item => item.id === message.id)
            if (index > -1) {
                state.messages = [
                    ...state.messages.slice(0, index),
                    ...state.messages.slice(index + 1)
                ]
            }
        }
    },
    actions: {
        async createMessageAction({commit, state}, message) {
            const result = await MessagesApi.create(message)
            const data = await result.json()
            const index = state.messages.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateMessageMutation', data)
            } else {
                commit('createMessageMutation', data)
            }

        },
        async updateMessageAction({commit}, message) {
            const result = await MessagesApi.update(message)
            const data = await result.json()
            commit('updateMessageMutation', data)
        },
        async deleteMessageAction({commit}, message) {
            const result = await MessagesApi.delete(message.id)
            if (result.ok) {
                commit('deleteMessageMutation', message)
            }
        }

    }

})