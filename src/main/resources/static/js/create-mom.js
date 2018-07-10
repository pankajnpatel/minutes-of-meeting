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

// Bootstrap
//Vue.use(BootstrapVue)
Vue.use(VeeValidate);

/* eslint-disable no-new */
var vm = new Vue({
    el: '#app',
    data: {
    	request: {
    		meetingTopic: '',
    		meetingLocation: '',
    		noteTaker: '',
    		fromDate: '',
    		toDate: '',
    		fromTime: '',
    		toTime: '',
    		topics: '',
    		sDepartment: '',
        	sProject: '',
        	sAttendee: '',
        	message: []
    	},
        orgProject: 
        	[{'id':'1', 'dept':'JAVA', 'name':'T-Mobile Groovy'}, {'id':'1', 'dept':'JAVA', 'name': 'T-Mobile Backend'}, 
                   {'id':'1', 'dept':'PHP', 'name':'T-Mobile EPC Tool'}, {'id':'1', 'dept':'.NET', 'name':'T-Mobile PIER'}],
        orgAttendees: 
        	[{'id':'1', 'dept':'JAVA', 'project':'T-Mobile Groovy', 'name':'Pankaj'}, {'id':'1', 'dept':'JAVA', 'project':'T-Mobile Groovy', 'name':'Vinit'}, 
                   {'id':'1', 'dept':'PHP', 'project':'T-Mobile EPC Tool', 'name':'Navneet'}, {'id':'1', 'dept':'.NET', 'project': 'T-Mobile PIER', 'name':'Uttam'}],
        attendees: [],
        projects: [],
        isExist: false
    },
    computed: {
    	isValidated : function(){
    		return !(!this.request.meetingTopic || !this.request.meetingLocation || !this.request.noteTaker || 
    		!this.request.fromDate || !this.request.toDate || !this.request.fromTime || !this.request.toTime ||
    		!this.request.topics || !this.request.sDepartment || !this.request.sProject || !this.request.sAttendee ||
    		this.request.message.length < 1)
    	}
    },
    methods: {
        'addAttendee' : function(){
        	var vm = this;
        	vm.isExist = false;
        	if (vm.request.message.length > 0){
            	vm.isExist = vm.request.message.filter(function(elem){
            	    if(elem.name == vm.request.sAttendee) return true;
            	}).length > 0;
        	}
			
        	if (!vm.isExist){
        		vm.request.message.push({'name': vm.request.sAttendee, 'project': vm.request.sProject, 'department': vm.request.sDepartment, 'present': true, 'reason': ''});
        	}
        },
    	'removeAttendee' : function(index){
            this.request.message.pop(index);
      	},
        'checkPresent' : function(event, index){
            console.info(this.request.message[index].present);
      	},
      	'changeDepartment' : function(){
      		var self = this;
      		self.projects = [];
      		self.attendees = [];
      		self.request.sProject = '';
      		self.request.sAttendee = '';
      		self.orgProject.forEach(function(item, index) {
      			if (item.dept == self.request.sDepartment){
      				self.request.sProject = item.name;
      				self.projects.push(item);
          		}
      		});
      	},
      	'changeProject' : function(){
      		var self = this;
      		self.isExist = false;
      		self.request.message = [];
      		self.attendees = [];
      		self.request.sAttendee = '';
      		self.orgAttendees.forEach(function(item, index) {
      			if (item.project == self.request.sProject){
      				self.attendees.push(item);
      				self.request.sAttendee = item.name;
          		}
      		});
      	},
      	'createMoM' : function(){
      		   console.info("request: ", this.request)
      	}
    }
});

vm.$watch('request.sDepartment', function (val) {
	vm.changeProject();
})