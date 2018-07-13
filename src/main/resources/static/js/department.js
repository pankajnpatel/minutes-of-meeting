Vue.config.productionTip = false

Vue.use(VeeValidate);


var vm = new Vue({
    el: '#app',
    data: {
    	
    	request: {
    		deptName: '',
    	},
    	deptList:[],
        isExist: false
    },
    beforeMount(){
    	var self = this;
    	
    	axios.get('/dept/all')
        .then(function (response) {
        	self.deptList = response.data;
        	var self = this;
        })
        .catch(function (error) {
            console.log(error.message);
        });

    },
    computed: {
    	isValidated : function(){
    		return !(!this.request.deptName )
    	}
    },
    methods: {
        
    	'removeDept' : function(index){
    		var self = this;
            var delObj = self.deptList[index];
            if(delObj != null && delObj != undefined){
            	axios.delete('/dept/del/'+delObj.id)
	            .then(function (response) {
	            	self.deptList.pop(index);
	            })
	            .catch(function (error) {
	                console.log(error.message);
	            });
            }
            
      	},
        'editDept':function(index){
        	var self = this;
        	self.request = self.deptList[index];
      	},
      	'createDept' : function(){
      		var self = this;
      		if (self.deptList.length > 0){
      			self.isExist = self.deptList.filter(function(elem){
            	    if(elem.deptName == self.request.deptName) return true;
            	}).length > 0;
      		}
      		if(self.request.id != null && self.request.id != undefined){
      			console.log(self.request);
                if(self.request != null && self.request != undefined){
                	axios.put('/dept/update/',self.request)
    	            .then(function (response) {
    	            	self.request = {};
    	            })
    	            .catch(function (error) {
    	                console.log(error.message);
    	            });
                }
      		}else{
      			if (!self.isExist){
    	      		axios.post('/dept/add',self.request)
    	            .then(function (response) {
    	            	self.deptList.push(response.data);
    	            	self.request.deptName = '';
    	            })
    	            .catch(function (error) {
    	                console.log(error.message);
    	            });
          		}
      		}

      	}
    }
});

vm.$watch('request.sDepartment', function (val) {
	vm.changeProject();
})