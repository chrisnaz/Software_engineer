<template>
    <v-layout column mt-5 ml-5 mr-5>
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> My State of the Art  </h1>
            </v-flex>
        </v-layout>
    
    <v-layout column>
        <v-flex>
        <v-list>
            <div v-if="loading">
            <v-flex>
                <v-layout align-center justify-center>
             <v-progress-circular
                :size="200"
                :width="5"
                color="red"
                indeterminate
                ></v-progress-circular>
                </v-layout>
            </v-flex>
            </div>
            <div v-else>
            <div v-if="listSOA.length ==0">
                <v-layout row align-start justify-space-between >
                    <v-flex xs9 mt-1>
                        <h4>You didn't add a SOA yet</h4>
                    </v-flex>
                </v-layout>
            </div>
            <v-list-tile v-for="soa in listSOA" :key="soa.id" class=" mb-3">
                <v-layout row align-start justify-space-between >
                    <v-flex xs10 mt-1>
                        <v-layout column class="light_grey_background">
                            <v-flex>
                        <span> {{soa.title}} - {{soa.date}}</span>
                            </v-flex>
                            <v-flex>
                            <span> {{soa.user.lastname}} {{soa.user.firstname}}<span v-for="author in soa.authors" :key="author.id">, {{author.lastname}} {{author.firstname}}</span></span>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex xs2 ml-3>
                        <v-layout row>

                        <v-btn large flat class="pink_custom" style="border-radius: 5px;" 
                         :href="'/Soa?id=' + soa.id"> 
                            <v-icon class="white--text"> launch </v-icon>
                        </v-btn>`<!--
                        <v-btn large flat class="white" style="border-radius: 5px;"> 
                            <v-icon class="pink_custom--text"> create </v-icon>
                        </v-btn>
                        

                        <v-btn large flat class="pink_custom" style="border-radius: 5px;"> 
                            <v-icon class="white--text"> delete </v-icon>
                        </v-btn>-->
                        </v-layout>
                    </v-flex>
                    
                </v-layout>

            </v-list-tile>
            </div>
        </v-list>
        </v-flex>
    </v-layout>
    </v-layout> 
</template>

<script>
import axios from 'axios'
import { setTimeout } from 'timers';
var test;
export default {
    data () {
        return {
            loading: true,
            id_user:null,
            listSOA: [
            ]
        }
    },created(){

        //Get ID value
        axios({
        method: 'GET',
        // http://localhost:8080/UsersName/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()

        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()

      }).then(response => {
          this.id_user = response.data.id;
      })
        var self = this;
      //Get all SOA from its ID
      setTimeout(function(){
        axios({
        method: 'GET',
        // http://localhost:8080/Users/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()

        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Users/' + self.id_user.toString() +'/StateOfArts'

      }).then(response => {
        test = response.data;
        self.change(response.data)
        
        
      })
      
      },2000)
      this.listSOA = self.listSOA;
      

    },methods:{
        change: function(list){
            this.listSOA= list
            this.loading = false;
        }
    }
    
}
</script>
