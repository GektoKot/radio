function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var messageAPI = Vue.resource('/messages{/id}');

Vue.component('message-form', {
    props: ['messages', 'messageAttributes'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageAttributes: function (newValue, oldValue) {
            this.text = newValue.text;
            this.id = newValue.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Radio message" v-model="text"/>' +
        '<input type="button" value="Broadcast!" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var message = {text: this.text};

            if (this.id) {
                messageAPI.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.text = ''
                        this.id = ''
                    }))
            } else {
                messageAPI.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div>' +
        '(<i>{{ message.id }}</i>) <b>{{ message.text }}</b>' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Renovation" @click="edit"/>' +
        '<input type="button" value="Extinguish" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            messageAPI.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template: '<div style="position: relative; width: 600px;">' +
        '<message-form :messages="messages" :messageAttributes="message"/>' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" ' +
        ':messages="messages" :editMethod="editMethod"/>' +
        '</div>',

    methods: {
        editMethod: function (message) {
            this.message = message;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<div>' +
                '<div v-if="!profile"><a href="/login">Enter</a> to the RaDiO station! </div>' +
                '<div v-else>' +
                    '<div><b>{{profile.name}}</b>  <a href="/logout">Exit</a> from RaDiO station!</div>' +
                    '<messages-list  :messages="messages"/>' +
                '</div>' +
            '</div>',
    data: {
        messages: frontendData.messages,
        profile: frontendData.profile
    },
    created: function () {
        // messageAPI.get().then(result => result.json().then(data =>
        //     data.forEach(message => this.messages.push(message))))
    }
});