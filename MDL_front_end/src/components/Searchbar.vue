<template>
    
        <v-layout column >
            <v-flex xs12>
                <v-layout align-center justify-center row wrap>
                <v-flex xs11>
                  
                    <v-text-field class="px-3" 
                                solo
                                flat
                                color="pink_custom" 
                                single-line 
                                label="Search by keywords ..."  
                                v-model="input"
                                @keyup.enter="show_visu"
                                style="border: 1px solid grey ; border-radius: 40px ; height: 64px; padding: 4px" >
                    </v-text-field>
                
                </v-flex>
                <v-flex  xs1>
                    <v-btn @click="show_visu" right fab flat class=" ml-4 pink_custom" color="white"> 
                        <v-icon> search </v-icon>
                    </v-btn>
                </v-flex>
                </v-layout>
                <span class="grey_message--text"> If field remain empty, all state of the art will be shown </span>
                <!-- <v-layout align-end justify-end column>
                <div v-if="!filter">
                <v-btn flat color="ligt_grey_custom" small v-on:click="filter = !filter"  >
                            <span> More criteria </span>
                            <v-icon right> keyboard_arrow_down </v-icon>
                        </v-btn>  
                </div>
                </v-layout>

                  
                <div v-if="filter" style="border: 1px solid grey ; border-radius: 25px; margin-bottom: 20px">

                        <div class="px-5 mt-3 mb-3 mr-0">
                            
                            <div style="margin-bottom: 15px">
                                <span> Search by: </span>
                            </div>
                                <v-text-field 
                                    box
                                    width=320
                                    label="Title"
                                    flat
                                    color="pink_custom"
                                    background-color="light_grey_background"
                                    ></v-text-field>
                                
                                <v-layout align-center justify-start>
                                    <span> Date Range : </span>
                                    <v-range-slider
                                        left
                                        color="pink_custom"
                                        v-model="dateRange"
                                        :max="2020"
                                        :min="1900"
                                        :step="5"
                                        class="full-width"
                                        style="width:100%; margin: 50px; padding-top: 20px; margin-bottom: 20px;"
                                        thumb-label="always">
                                    </v-range-slider>
                                </v-layout>

                                <v-layout align-end justify-end row fill-height>
                                                
                                                         <v-btn flat round color="ligt_grey_custom" small v-on:click="filter = !filter">
                                                            <span> Less Criteria </span>
                                                            <v-icon right > keyboard_arrow_up </v-icon>
                                                        </v-btn>    
                                                    
                                                </v-layout>
                            
                        </div>        
                    
                </div>-->
            </v-flex>
        </v-layout>
    


</template>

<script>
import router from '../router'
export default {
    data() {
        return {
           input:'',
           author : '',
           category : '',
           thematic : '',
           title : '',
           domain : '',
           subdomain : '',
           dateRange : [1900,2020],
           filter: false
           
        }
    },methods:{
        show_visu: function(){
            var all_inputs = [];

            all_inputs =this.input.split(" ");
            console.log(all_inputs);
            /*var link = "/search?input="
            for (const key in all_inputs) {
                if (key == all_inputs.length-1){
                    link += all_inputs[key]
                }else{
                    link += all_inputs[key] +","
                }
                
            }
            router.push(link);
            */

            router.push({
                path: '/search',
                query: {
                    tags: all_inputs.join(',')
                }
                });
        
        }

    },
    
}
    

</script>