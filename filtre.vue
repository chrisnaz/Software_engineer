<template>
    
        <v-layout column >
            <v-flex xs12 >
                <v-layout align-center justify-center row wrap>
                <v-flex xs11>
                    <v-text-field class="mt-3 px-3" 
                                
                                flat
                                color="pink_custom" 
                                single-line 
                                label="search..."  
                                
                                style="border: 1px solid black ; border-radius: 40px ; height: 64px" >
                    </v-text-field>
                </v-flex>
                <v-flex xs1>
                    <v-btn right fab flat class="ml-4 mt-4 mb-4 pink_custom" color="white"> 
                        <v-icon> search </v-icon>
                    </v-btn>
                </v-flex>
                </v-layout>

                <v-layout align-end justify-end column>
                <div v-if="!filter">
                <v-btn flat color="ligt_grey_custom" small v-on:click="filter = !filter"  >
                            <span> Criteria </span>
                            <v-icon right> filter_list </v-icon>
                        </v-btn>  
                </div>
                </v-layout>
                <div v-if="filter">
                <v-layout align-start column style="border: 1px solid black ; border-radius: 25px" >
                    
                          
                        
                        
                        <v-card flat class="px-5 mt-3 mb-3"  >
                            
                            <span class="mx-3"> Search by: </span>
                            
                                
                                    <v-layout row wrap fill-height >
                                        <v-flex xs4 sm4 md4 shrink>
                                            <v-layout column wrap fill-height >
                                                <v-flex xs6 sm2 md2 shrink>
                                            <v-checkbox v-model="author" label="Author"  value="author-1"> </v-checkbox>
                                            <v-checkbox v-model="category" label="Category" value="category" > </v-checkbox>
                                            <v-checkbox v-model="thematic" label="Thematic" > </v-checkbox>
                                                </v-flex>
                                            </v-layout>
                                        </v-flex>
                                        
                                        <v-flex xs4 sm4 md4 >
                                            <v-checkbox v-model="title" label="Title" > </v-checkbox>
                                            <v-checkbox v-model="domain" label="Domain" > </v-checkbox>
                                            <v-checkbox v-model="subdomain" label="Sub-Domain" > </v-checkbox>
                                        </v-flex>
                                        
                                        <v-flex xs4 sm4 md4>
                                            <v-layout column style="height: 185px; width: 200px">
                                                <v-layout row justify-start >
                                                    <v-flex xs6 class="mt-3"> 
                                                    <span> Date Range </span>
                                                    </v-flex>
                                                    
                                                    <v-flex xs6>
                                                    <v-range-slider
                                                        right
                                                        v-model="dateRange"
                                                        :max="2020"
                                                        :min="2000"
                                                        :step="1"
                                                        
                                                        style="width:120px"
                                                        thumb-label="always"
                                                        >
                                                    </v-range-slider>
                                                    </v-flex>
                                                    
                                                        
                                                </v-layout>

                                                <input type="text" v-model="search_filter" placeholder="filtre">


                                                <v-layout align-end justify-end row fill-height>
                                                
                                                         <v-btn flat round color="ligt_grey_custom" small v-on:click="filter = !filter">
                                                            <span> Less Criteria </span>
                                                            <v-icon right > arrow_back_ios </v-icon>
                                                        </v-btn>    
                                                    
                                                </v-layout>
                                            </v-layout>

                                        </v-flex>    
                                    </v-layout>
                                    
                        </v-card>  
                    
                </v-layout>
                </div>
                <v-flex xs12 sm6 offset-sm3>
                    <v-card>
                        <v-card-title>
                            <div v-for="blog in filteredAll" :key="blog" class="single-stateofart">
                                <h4>{{ blog.title }} , {{ blog.date }}</h4>
                                <div v-for="author in blog.authors" :key="author" >
                                    <p>{{ author.firstname }}</p>
                                    <p>{{ author.lastname }}</p>
                                    <p>{{ author.email }}</p>
                                </div>
                            </div>
                        </v-card-title>
                    </v-card>
                </v-flex>    
            </v-flex>
        </v-layout>


</template>

<script>
import DataJson from "../json/StateOfArts.json";
import { debug } from 'util';
export default {
    data() {
        return {
           author : '',
           category : '',
           thematic : '',
           title : '',
           domain : '',
           subdomain : '',
           dateRange : [2000,2020],
           //dateRange : [1900,2020],
           filter: false,
           blogs: [],
           search_filter: ''
        }
    },
    created() {
        //this.$http.get('http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts').then(function(data){
            //console.log(data);
            //this.blogs = data.body.slice(0,10)
        //})
        //console.log(DataJson)
        this.blogs = DataJson.slice(0,10)
    
    },
    computed: {
        
        /**
         * This function mix the two filtre : 
         *      fiteredDate : for the date range
         *      filteredTitleAndAuthor : for authors and title
         */
        filteredAll: function(){
            let lui = ""
            let tilAuthor = this.filteredTitleAndAuthor
            let date = this.filteredDate
            if(this.search_filter != lui)
                return tilAuthor
            else
                return date
        },
        /**
         * Function for filtered date
         */
        filteredDate: function() {
            debugger
            return this.blogs.filter((data) => {
                debugger
                // Take only the 4 last digit of date
                let date = Number.parseInt(data.date.slice(-4))
                return date <= this.dateRange[1] && date >= this.dateRange[0];            
            })
        },
        /**
         * Function for filter the title and authors
         */
        filteredTitleAndAuthor: function() {
            let list = this.blogs.filter((data, index ) => {
                // The filter of title
                let isInTitle = data.title.includes(this.search_filter)
                // The filter of authors, remove article that have empty authors
                let isInAuthors = data.authors.length != 0 && data.authors.every(author =>{
                    return author.firstname.includes(this.search_filter) ||
                        author.lastname.includes(this.search_filter)
                })
                return isInTitle || isInAuthors
            })
            return list;
        }
            
    /*methods:{
        test: function(){console.log("coucou")
        console.log(this.dateRange);
        console.log("test")
        }
    }*/
    }
}
        
</script>