<template>
    <v-layout column mt-5 ml-5 mr-5>
        <!-- titre -->
        <v-layout align-start justify-start row>
            <v-flex xs3 >
                <h1 class="middle_pink--text"> Create New State of the Art
                    <v-icon large right class="middle_pink--text mt-1"> keyboard_arrow_right </v-icon>
                </h1>
            </v-flex>
            <v-flex xs3>
                
                <h1 class="pink_custom--text"  >Classification Scheme </h1>
            </v-flex>   
        </v-layout>

        
        <v-layout column>
            <!-- Buttons -->
            <v-layout ml-5 mt-3 align-start justify-start row>
                <v-flex xs3>
                    <v-btn class="white" v-on:click="left = true ; right = false" > <h3> Criterias </h3> </v-btn>
                    <v-btn class="white" v-on:click="left = false ; right = true" > <h3> Articles </h3> </v-btn>
                </v-flex>
            </v-layout>
            <!-- list of terms -->
            <v-layout>
                <v-flex xs12 v-if="left">
                    <v-card class="pa-3 mb-5">
                        <v-layout row align-start justify-space-between v-for="term in terms" :key="term.label" mx-5 >
                            <v-flex xs11>
                                <v-text-field hide-details box color="pink_custom" background-color="light_grey_background" class="mb-3" :value="term.label" :v-model="term.model" > 
                                        
                                </v-text-field>
                            </v-flex>
                            <v-flex xs1 ml-2>
                                <!-- button delete NOT IMPLEMENTED -->
                                <v-btn large flat class="pink_custom" style="border-radius: 5px;"> 
                                    <v-icon class="white--text"> delete </v-icon> 
                                </v-btn>
                            </v-flex>
                        </v-layout> 
                        <!-- Button more NOT IMPLEMENTED -->
                        <v-layout align-end column>
                            <v-flex xs1>
                                <v-btn round flat class="pink_custom white--text"> 
                                    <v-icon> add_box </v-icon>
                                </v-btn>
                            </v-flex>
                        </v-layout>
                        <hr class="my-3">
                        <!-- foot button -->
                        <v-layout align-end  column>
                            <v-flex xs1 >
                                <v-btn flat round to="/newSoa" outline class="mr-5" > 
                                   <span> back </span>
                                </v-btn>
                                <v-btn flat round class="pink_custom" v-on:click="left = false ; right = true">
                                    <span class="white--text"> Next </span>
                                </v-btn>
                            </v-flex>
                        </v-layout>
                    </v-card>
                </v-flex>    
            </v-layout>
            <!-- articles -->
            <v-layout>
                <v-flex xs12 v-if="right">
                    <v-card class="pa-3 mb-5">
                        <v-card flat class="pa-3 mb-5">
                            <v-layout row align-start justify-space-between mx-5>
                                <!-- texts field -->
                                <v-flex xs10>
                                    <v-layout column>
                                        <v-flex xs10 mb-2>
                                        <v-layout row align-start justify-space-between>
                                            
                                            <v-flex xs1>
                                                <h3 class="mt-4"> Article Title : </h3>
                                            </v-flex>
                                            <v-flex xs11>
                                                <v-text-field flat 
                                                            hide-details 
                                                            height="35px" 
                                                            single-line 
                                                            color="pink_custom" 
                                                            background-color="light_grey_background" 
                                                            > 
                                                </v-text-field>
                                            </v-flex>    
                                        </v-layout>
                                        </v-flex>
                                        <v-flex xs10>
                                            <v-layout row>
                                                <v-flex xs1>
                                                    <v-layout column>
                                                        <v-flex>
                                                        <h3 class="mt-4"> Criterias : </h3>
                                                        </v-flex>
                                                    </v-layout>
                                                </v-flex>
                                                <v-flex xs1>
                                                    <v-layout column>
                                                        <v-flex>
                                                        <h3 class="mt-4"> Study Year </h3>
                                                        </v-flex>
                                                        <v-flex>
                                                        <h3 class="mt-4"> Authors </h3>
                                                        </v-flex>
                                                        <v-flex>
                                                        <h3 class="mt-4"> Tools </h3>
                                                        </v-flex>
                                                    </v-layout>
                                                </v-flex>
                                                <v-flex xs10>
                                                    <v-layout column>
                                                        <v-flex xs12>
                                                            <v-text-field flat 
                                                                        hide-details 
                                                                        height="35px" 
                                                                        single-line 
                                                                        color="pink_custom" 
                                                                        background-color="light_grey_background" 
                                                                        > 
                                                            </v-text-field>
                                                        </v-flex>
                                                        <v-flex xs12>
                                                            <v-text-field flat 
                                                                        hide-details 
                                                                        height="35px" 
                                                                        single-line 
                                                                        color="pink_custom" 
                                                                        background-color="light_grey_background" 
                                                                        > 
                                                            </v-text-field>
                                                        </v-flex>
                                                        <v-flex xs12>
                                                            <v-text-field flat 
                                                                        hide-details 
                                                                        height="35px" 
                                                                        single-line 
                                                                        color="pink_custom" 
                                                                        background-color="light_grey_background" 
                                                                        > 
                                                            </v-text-field>
                                                        </v-flex>
                                                    </v-layout>
                                                </v-flex>
                                                
                                            </v-layout>
                                        </v-flex>

                                        
                                    </v-layout>
                                </v-flex>
                                <!-- button on right  NOT IMPLEMENTED -->
                                <v-flex xs2>
                                    <v-btn large flat class="white" style="border-radius: 5px;"> 
                                        <v-icon class="pink_custom--text"> delete </v-icon> 
                                    </v-btn>
                                <v-btn large flat class="pink_custom white--text" style="border-radius: 5px;"> 
                                        <v-icon> add_box </v-icon>
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-card>
                        <hr ma-3>

                        <v-layout align-end  column>
                            <v-flex xs1 >
                                <v-btn flat round v-on:click="left = true ; right = false" outline class="mr-5" > 
                                   <span> back </span>
                                </v-btn>
                                <!-- NOT IMPLEMENTED -->
                                <v-btn flat round class="pink_custom">
                                    <span class="white--text"> Apply </span>
                                </v-btn>
                            </v-flex>
                        </v-layout>

                    </v-card>
                </v-flex>
            </v-layout>
            

        </v-layout>
    </v-layout>
</template>

<script>
export default {
    data (){
        return {

            left: true,
            right: false,
            disable: true,

            terms:[
                { label:'term1', model: ''},
                { label:'term2', model: ''},
                { label:'term3', model: ''},
                { label:'term4', model:''},
                { label:'term5', model:''}
            ],
        }
    }
}
</script>
