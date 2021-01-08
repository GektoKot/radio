import Vue from 'vue'
import Vuex from 'vuex'
import MessagesApi from 'api/messages.js'
import CommentApi from 'api/comment.js'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        messages,
        profile,
        // profile: frontendData.profile
        ...frontendData
    },
    getters: {
        sortedMessages: state =>  (state.messages || []).sort((a, b) => -(a.id - b.id))
        // sortedMessages: state =>  state.messages.sort((a, b) => -(a.id - b.id))
        // sortedMessages: state =>  state.messages
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
        },
        createCommentMutation(state, comment) {
            const index = state.messages.findIndex(item => item.id === comment.message.id)
            const message = state.messages[index]

            if (!(message.comments || []).find(it => it.id === comment.id)) {
                state.messages = [
                    ...state.messages.slice(0, index),
                    {
                        ...message,
                        comments: message.comments === null ? [comment] : [...message.comments, comment]
                        // comments: [
                        //     ...message.comments,
                        //     comment
                        // ]
                    },
                    ...state.messages.slice(index + 1)
                ]
            }
        },
        addMessagePageMutation(state, messages) {
            const targetMessages = state.messages
                .concat(messages)
                .reduce((result, value) => {
                    result[value.id] = value
                    return result
                }, {})
            state.messages = Object.values(targetMessages)
        },
        updateTotalPagesMutation(state, totalPages) {
            state.totalPages = totalPages
        },
        updateCurrentPageMutation(state, currentPage) {
            state.currentPage = currentPage
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
        },
        async createCommentAction({commit, state}, comment) {
            const response = await CommentApi.create(comment)
            const data = await response.json()
            commit('createCommentMutation', data)
        },
        async loadPageAction({commit, state}) {
            const response = await  MessagesApi.page(state.currentPage +1)
            const data = await response.json()

            commit('addMessagePageMutation', data.messages)
            commit('updateTotalPagesMutation', data.totalPages)
            commit('updateCurrentPageMutation', Math.min(data.currentPage, data.totalPages - 1))
            // commit('updateCurrentPageMutation', data.currentPage)
        }
    }
})