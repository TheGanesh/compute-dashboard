  var callDescribeEC2API = function() {

          $.ajax({
                    url: "/compute-dashboard/api/describe/ec2instances",
                    type:"GET",
                    headers: {
                       "X-AUTH-TOKEN":"sampleToken"
                     },
                    success: function(data){

                         $('#loginDiv').hide();
                         $('#contentDiv').show();

                          $('#EC2Table').DataTable( {
                          data: data,
                          columns: [
                                     { data: 'name' },
                                     { data: 'id' },
                                     { data: 'type' },
                                     { data: 'state' },
                                     { data: 'az' },
                                     { data: 'publicIp' },
                                     { data: 'privateIp' }
                                   ]
                          } );
                           },
                         error: function(){
                                 $('#contentDiv').hide();
                                 $('#loginDiv').show();
                                 $('#loginFailure').hide();
                                 $('#oopsError').show();

                          }
                 });
       };


     var callAuthAPI = function(){
                $.ajax({
                    url: "/compute-dashboard/api/auth",
                    type:"POST",
                    headers: {
                       "X-AUTH-TOKEN":"sampleToken"
                     },
                    data:{username: $('#username').val(),password: $('#password').val()  },
                    success: function(){
                              $('#loginDiv').hide();
                              callDescribeEC2API();
                             },
                              error: function(){
                                $('#loginFailure').show();
                                $('#oopsError').hide();
                              }
                 });
      };