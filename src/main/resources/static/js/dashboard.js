// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//import Vue from './lib/vue';
//import App from './App'
//import router from './router'
//import BootstrapVue from 'bootstrap-vue'
//import 'bootstrap/dist/css/bootstrap.css'
//import 'bootstrap-vue/dist/bootstrap-vue.css'

//import VeeValidate from './resources/js/lib/vee-validate'

Vue.config.productionTip = false

Vue.use(VeeValidate);

/* eslint-disable no-new */
var vm = new Vue({
    el: '#app',
    data: {
    	
    	request: {
    		
    	},
    	meetingList: [],
       
        isExist: false
    },
    beforeMount(){
    	var self = this;
    	
    	axios.get('/user-taker/9/meeting')
        .then(function (response) {
        	self.meetingList = response.data;
        })
        .catch(function (error) {
            console.log(error.message);
        });

    },
    
    methods: {
    	
      	'deleteMeeting' : function(index){
      		var self = this;
      		
      		var meetingId = self.meetingList[index].id;
      		self.meetingList.splice(index,1);

      		axios.delete("/meeting/del/"+meetingId)
            .then(function (response) {
            	
            })
            .catch(function (error) {
                console.log(error.message);
            });

      	},
      	'editMeeting' : function(index){
      		var self = this;
      		window.location = "mom/update/"+ self.meetingList[index].id;
      	}
    }
});

vm.$watch('request.sDepartment', function (val) {
	vm.changeProject();
})