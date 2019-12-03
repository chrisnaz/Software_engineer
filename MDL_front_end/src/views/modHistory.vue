<template>
    <v-layout column mt-5 ml-5 mr-5>
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> Modification History </h1>
            </v-flex>
        </v-layout>
    
        <v-layout column mt-4>
            <v-flex>
                <v-layout align-center row class="middle_pink" style="height: 50px" pl-4 >
                    <v-flex  xs6>
                        <h4 class="font-weight-regular white--text"> Date </h4>
                    </v-flex>
                    <v-flex xs6>
                        <h4 class="font-weight-regular white--text"> Modification Type </h4>
                    </v-flex>
                </v-layout>
            </v-flex>
            <v-flex>
            <v-list>
                <v-list-tile style="height: 35px" v-for="mod in modifications" :key="mod.date">
                    <v-layout row align-start justify-space-between >
                        <v-flex  mt-1>
                            <v-layout row class="light_pink_background" pl-4>
                                <v-flex xs6>
                                        
                                        <span> {{ mod.dateTime[2] + "/" + mod.dateTime[1] + "/" + mod.dateTime[0]}} </span>
                                        <span> {{mod.dateTime[3] + ":" + mod.dateTime[4]}}</span>
                                </v-flex>

                                <v-flex xs6>
                                    <span> {{mod.modification}} </span>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        
                        
                    </v-layout>

                </v-list-tile>
            </v-list>
            </v-flex>
        </v-layout>
        <hr>
        <v-layout row justify-end> 
            <v-btn 
                round 
                outline 
                class="cancel-btn ligt_grey_custom" 
                flat
                :href="this.back">Back</v-btn>
        </v-layout>
    </v-layout> 
</template>

<script>
import axios from 'axios'
export default {
    data () {
        return {
            
            author: "John Doe",
            idSOTA: 5,
            back:'',
            modifications : [
            ]
        }
    },
    created(){
        let urlParams = new URLSearchParams(window.location.search);
        let id = urlParams.get('id');
        let key_search = urlParams.get('tags')
        this.back = "search?tags="+ key_search;
         axios({
        method: 'GET',
        // http://localhost:8080/StateOfArts/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+ id.toString() + "/history"
      }).then(response => {
        this.idSOTA = id;
        this.getModHistory(id);

      })
         
    },
    methods: {
        getModHistory: function(id) {
        axios({
        method: 'GET',
        // http://localhost:8080/StateOfArts/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +"/articles"
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+ id.toString() + "/history"
      }).then(response => {
        this.modifications = response.data.reverse();
      })
     }
    }
    
}
</script>
