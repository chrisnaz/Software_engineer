<template>
   <v-layout column mt-5 ml-5 mr-5>
        <v-layout align-start row>
            <v-flex xs11>
                <h1 class="pink_custom--text"> View Classification Scheme </h1>
            </v-flex>
            <v-flex xs1>
                <v-layout justify-end >
                <v-btn small flat fab class="white" to="/newSoa">
                    <v-icon  class="pink_custom--text" > clear
                    </v-icon>
                </v-btn>
                </v-layout>
            </v-flex>
        </v-layout>

        <v-layout column  mt-5>
            <v-flex xs10 >
                <v-layout align-start justify-start class="middle_pink" >
                    <v-flex xs4 >
                        <h2 class="font-weight-regular white--text ml-3"> Article Titles </h2>
                    </v-flex>
                    <v-flex xs2 >
                        <h2 class="font-weight-regular white--text"> Study Year </h2>
                    </v-flex>
                    <v-flex xs3 >
                        <h2 class="font-weight-regular white--text">Authors </h2>
                    </v-flex>
                    <v-flex xs3 >
                        <h2 class="font-weight-regular white--text"> Tools </h2>
                    </v-flex>
                </v-layout>
            </v-flex>
            <v-flex xs10 mt-1 >
                <v-list dense v-for="article in articles" :key="article.title_art">
                    
                        <v-layout align-start justify-start class="light_pink_custom" >
                            <v-flex xs4 >
                                <h4 class="font-weight-regular ml-3">  {{article.title_art}} </h4>
                            </v-flex>
                            <v-flex xs2 >
                                <h4 class="font-weight-regular"> {{article.date_art}} </h4>
                            </v-flex>
                            <v-flex xs3 >
                                <h4 class="font-weight-regular ">{{article.author_art}} </h4>
                            </v-flex>
                            <v-flex xs3 >
                                <h4 class="font-weight-regular "> {{article.tags_art}} </h4>
                            </v-flex>
                        </v-layout>
                    
                </v-list>
                
            </v-flex>

            
        </v-layout>
        
   </v-layout>
</template>

<script>
export default {
    data (){
     return {
         articles: [
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},
             {title_art:'L\'informatique à l\'école',date_art:'2018',author_art:'John Doe et al',tags_art:' Lorem Ipsum dolor et sicarnet'},

             
             ]
         
     }
    }
}
</script>
