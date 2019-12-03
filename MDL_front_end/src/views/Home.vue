<template>
  <v-layout column>

    <v-layout align-end column mt-2 mr-2 v-if="accessToken">
      <v-btn flat large to="/newSoa"> 
        <h4 class="pink_custom--text"> Create New State of the Art </h4>
        <v-icon class="ml-3" color="pink_custom"> add_circle </v-icon>
      </v-btn>
      
    </v-layout>
    <v-layout  align-center justify-center column>
      <v-flex xs12 sm6 md4 class="mt-3">
        <img src="/etadelard_logo.png" width="606px" height="138px" alt="image trop cool du logo">
        
        <Searchbar class="mt-5"/>


        <div v-if="accessToken" class="mt-5">
          <h4 v-if="historicsLength" class="pink_custom--text" style="margin-top:20px"> Visualizations saved </h4>
          <span v-if="historicsLength" class="grey_message--text"> There are your saved visualizations. Only 5 will be kept, if you have more than 5 the oldest will be deleted </span>

         <v-layout row justify-center align-center fill-height>

        <!-- Saved visu historic -->

        <v-flex xs12>
                
          <v-btn class="home_button_background text-md-center"
                    style="min-height: 150px; height:auto; width: 150px" 

                    v-for="historic in historics" :key="historic.id" router :to=visuRoute(historic.keywords)
                    round
                    flat>
             
               
               
                   
            <v-layout column justify-center align-center class="ml-1">
              
              <v-flex> 
                <span> {{ historic.date }} </span> 
                <li v-for="keyword in historic.keywords"  :key="keyword" style="list-style:none;">
                      {{ keyword }}
                </li>
              </v-flex>
              
            </v-layout>
          </v-btn>
               
        </v-flex>

    </v-layout>
        </div>
      </v-flex>
    </v-layout>
  </v-layout>



</template>

<script>
 
import axios from 'axios'

import Searchbar from '@/components/Searchbar'
import { mapState} from 'vuex';
export default {
   name: 'B',
  data () {
    return {
      message:'old one', 
      historicsLength: false,
      historics : [ ]
            
      }
  },
  components : {Searchbar},
    

  computed: {
      ...mapState([
        'accessToken'])
  },
  methods: {
      visuRoute(path){

        return '/search?tags='+path
      },
      getHistory: function(history){
        this.historics = history;

        if (this.historics.length > 0){
            this.historicsLength = true;
        } else {
            this.historicsLength = false;
        }  
      }
  },
  mounted: function () { 
        axios({
        method: 'GET',
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
      }).then(response => {
          this.id_user = response.data.id;
      })
       var self = this;
        setTimeout(function(){
          axios({
              method: 'GET',
              //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/saveResearch/keyword/'+ tags_list +'/user/'+ self.id_user.toString()
              url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/saveResearch/keyword/' + self.id_user.toString(),
              headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
              }
            }).then(response => {
              var clean_history = [];
              var split_keywords= [];
              for (const key in response.data.slice(-5)) { 
                  clean_history.push({id: response.data[key].id,date:response.data[key].date,keywords:response.data[key].keyword.split(' ')})
                
              }
              console.log(clean_history)
                self.getHistory(clean_history)
             
      })
         },1000)
    this.dialog = false
    this.saved = true;


  }
  
}
  
</script>