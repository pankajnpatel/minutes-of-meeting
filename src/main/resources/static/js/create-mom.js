Vue.config.productionTip = false

Vue.use(VeeValidate);

var vm = new Vue({
    el: '#app',
    data: {
    	
    	request: {
    		title:'',
    		agenda:'',
    		location : '',
    		meetingTaker : 0,
    		userMeeting : [],
    		fromDate:'',
    		toDate:'',
    		fromTime:'',
    		toTime:'',
    		sDepartment: 0,
        	sProject: 0
        	
    	},
    	orgDepartments: [],
        orgProject: 
        	[{'id':'1', 'dept':'JAVA', 'name':'T-Mobile Groovy'}, {'id':'1', 'dept':'JAVA', 'name': 'T-Mobile Backend'}, 
                   {'id':'1', 'dept':'PHP', 'name':'T-Mobile EPC Tool'}, {'id':'1', 'dept':'.NET', 'name':'T-Mobile PIER'}],
        orgAttendees: 
        	[{'id':'1', 'dept':'JAVA', 'project':'T-Mobile Groovy', 'name':'Pankaj'}, {'id':'1', 'dept':'JAVA', 'project':'T-Mobile Groovy', 'name':'Vinit'}, 
                   {'id':'1', 'dept':'PHP', 'project':'T-Mobile EPC Tool', 'name':'Navneet'}, {'id':'1', 'dept':'.NET', 'project': 'T-Mobile PIER', 'name':'Uttam'}],
        allMeetingTakers: [],
        attendees: [],
        projects: [],
        isExist: false
    },
    beforeMount(){
    	var self = this;
    	
    	var meetingId = document.getElementById("meetingId").value;
    	if(meetingId != undefined && meetingId != null ){
    		axios.get('/meeting/'+ meetingId)
            .then(function (response) {
            	self.request = response.data;
            	self.request.meetingTaker = response.data.meetingTaker.id;
            	setRequest(response.data);
            })
            .catch(function (error) {
                console.log(error.message);
            });
    	} 
    	
    	axios.get('/user/all')
        .then(function (response) {
        	self.allMeetingTakers = response.data;
        })
        .catch(function (error) {
            console.log(error.message);
        });
    	
    	axios.get('/user/all')
        .then(function (response) {
        	self.allMeetingTakers = response.data;
        })
        .catch(function (error) {
            console.log(error.message);
        });
    	
    	axios.get('/dept/all')
        .then(function (response) {
        	self.orgDepartments = response.data;
        })
        .catch(function (error) {
            console.log(error.message);
        });
    	
    },
    computed: {
    	isValidated : function(){
    		return !(!this.request.title || !this.request.location || !this.request.meetingTaker || 
    		!this.request.fromDate || !this.request.toDate || !this.request.fromTime || !this.request.toTime ||
    		!this.request.agenda || !this.request.sDepartment || !this.request.sProject || //!this.request.sAttendee ||
    		this.request.userMeeting.length < 1)
    	}
    },
    methods: {
        'addAttendee' : function(){
        	var vm = this;
        	vm.isExist = false;
        	if (vm.request.userMeeting.length > 0){
            	vm.isExist = vm.request.userMeeting.filter(function(elem){
            	    if(elem.attendeeName == vm.request.sAttendee.firstName) return true;
            	}).length > 0;
        	}
			
        	if (!vm.isExist){
        		vm.request.userMeeting.push({'attendee': vm.request.sAttendee.id ,'department':vm.request.sDepartment,'project':vm.request.sProject, 'attendeeName':vm.request.sAttendee.firstName, 'isPresent': true, 'reason': ''});
        	}
        },
    	'removeAttendee' : function(index){
            this.request.userMeeting.pop(index);
      	},
        'checkPresent' : function(event, index){
            console.info(this.request.userMeeting[index].isPresent);
      	},
      	'changeDepartment' : function(e){
      		var deptId = event.target.value;
      		var self = this;
      		self.projects = [];
      		self.attendees = [];
      		self.request.sProject = '';
      		self.request.sAttendee = '';
      		
      		axios.get('/dept/'+ deptId +'/proj')
            .then(function (response) {
            	
            	self.orgProject = response.data;
            	
            	self.orgProject.forEach(function(item, index) {
            		
            		self.request.sProject = item.id;
      				self.projects.push(item);
      				
          		});
            })
            .catch(function (error) {
                console.log(error.userMeeting);
            });
      		
      		self.loadAttendee();
      	},
      	'changeProject' : function(){
      		var self = this;
      		self.isExist = false;
      		self.request.userMeeting = [];
      		self.attendees = [];
      		self.request.sAttendee = '';
      		self.orgAttendees.forEach(function(item, index) {
      			if (item.project == self.request.sProject){
      				self.attendees.push(item);
      				self.request.sAttendee = item.name;
          		}
      		});
      		self.loadAttendee();
      	},
      	'loadAttendee' : function(){
      		var self = this;
      		self.request.sProject = (self.request.sProject != "" && self.request.sProject != undefined) ? self.request.sProject : 0 ; 
      		self.request.sDepartment =(self.request.sDepartment != "" && self.request.sDepartment != undefined)? self.request.sDepartment : 0  ;

      		if(self.request.sProject != 0 || self.request.sDepartment != 0 ){
	      		axios.get("/dept/" + self.request.sDepartment + "/proj/" + self.request.sProject)
	            .then(function (response) {	
	            	self.attendees = response.data;
	            })
	            .catch(function (error) {
	                console.log(error.message);
	            });
      		}
      	},
      	'createMoM' : function(){
      		
      		var vm = this;
      		axios.post("/meeting/custom/add",vm.request)
            .then(function (response) {
            	self.attendees = response.data;
            	alert("Meeting Succesfully created")
            })
            .catch(function (error) {
                console.log(error.message);
            });
      	},
      	'setRequest' : function(meetingObj){
      		var self = this; 
      		
      		self.request = meetingObj;
      	}
      	
    }
});

vm.$watch('request.sDepartment', function (val) {
	vm.changeProject();
})