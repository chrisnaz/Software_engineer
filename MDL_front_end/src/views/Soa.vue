<template>
<v-layout column mt-5 ml-5 mr-5>
    <!-- Titre + icone fav -->
    <v-layout align-start justify-space-around row mb-4>
        <v-flex xs11 >

            <div v-if="!(this.back.slice(-4)=='null')">
              <v-btn round outline class="cancel-btn ligt_grey_custom" flat :href="this.back">Back</v-btn>
            </div> 
            <div v-else>
             <v-btn round outline class="cancel-btn ligt_grey_custom" flat href="/mySoa">Back</v-btn>      
            </div>

        <h1 class="pink_custom--text"> {{titre}} </h1>
            
        </v-flex>
        <v-flex xs1>
        
            <v-icon class="pink_custom--text"
                    v-model="favorite" 
                    v-on:click="favorite = !favorite"
                    v-if="favorite==false"> 
                    favorite_border 
                    </v-icon>
            
            <v-icon class="pink_custom--text" 
                    v-model="favorite"
                    v-on:click="favorite = !favorite"
                    v-if="favorite==true">
                    favorite 
                    </v-icon>
        </v-flex>
    </v-layout>
    
    <v-layout ml-5 align-start row >
            <!-- Création Date, Author, Establishment -->
            <v-flex xs5 mr-5 >
                <v-layout column mt-5>
                    <v-flex  >
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Creation Date : </h4>
                            </v-flex>
                            <v-flex  xs8>
                                <a :href="'/modHistory?id='+idSOA+'&tags='+redirect_tags">
                                    <h4 class="font-weight-light pink_custom--text">{{creaDate}} </h4>
                                </a>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-2 xs1 >
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Author : </h4>
                            </v-flex>
                            <v-flex  xs8>
                                <a :href="'/profile?id='+authorId">
                                    <h4 class="font-weight-light pink_custom--text">{{author}} </h4>   
                                </a>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-2 xs1 >
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Establishment  : </h4>
                            </v-flex>
                            <v-flex  xs8>
                                    <h4 class="font-weight-light " >{{establishment}} </h4>                           
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
            <!-- LastModDate, co-author -->
            <v-flex xs5 mr-5 >
                <v-layout fill-height column mt-5>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs6 >
                                <h4 class="font-weight-regular"> Last Modification Date : </h4>
                            </v-flex>
                            <v-flex  xs6>
                                <a :href="'/modHistory?id='+idSOA+'&tags='+redirect_tags">
                                    <h4 class="font-weight-light pink_custom--text" >{{lastModDate}} </h4>  
                                </a> 
                            </v-flex>
                        </v-layout>
                    </v-flex>
                
                    <v-flex mt-2>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs6 >
                                <h4 class="font-weight-regular"> Co-Authors :</h4>
                            </v-flex>
                            <v-flex  xs6>
                                <v-layout  row >
                                
                                    <h4 v-for="coauteur in coAuthor" :key="coauteur.name" class="font-weight-regular pink_custom--text"> 
                                        {{coauteur.firstname}} {{coauteur.lastname}} {{doubleSpace}}
                                    </h4>     
                                
                                </v-layout>
                            </v-flex>
                        </v-layout>
                        
                    </v-flex>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs6 >
                                <h4 class="font-weight-regular"> Number of articles </h4>
                            </v-flex>
                            <v-flex  xs6>
                                    <h4 class="font-weight-light" >{{nbarticle}} </h4>   
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        
        </v-layout> 
    
    <hr class="mt-5 mb-5" >
    <!-- Abstract est true -->
    <v-layout align-center justify-center column mb-5 v-if="left==true">
        <v-flex>
            <v-btn flat 
                   style="border: 1px solid #7F7F7F; border-top-left-radius: 25px; border-bottom-left-radius: 25px"
                   class="pink_custom white--text"
                    >
                   
                <v-icon> notes</v-icon>
                <span> Abstract </span>
            </v-btn>
            <v-btn flat
                   style="border: 1px solid #7F7F7F; border-top-right-radius: 25px; border-bottom-right-radius: 25px"
                   v-on:click="right = true ; left = false"
                   class="ligt_grey_custom--text"> 
                <v-icon> list </v-icon>
                <span> Articles </span>
            </v-btn>
        </v-flex>
    </v-layout>
    <!-- Articles btn true -->
    <v-layout align-center justify-center column mb-5 v-if="right==true">
        <v-flex>
            <v-btn flat 
                   style="border: 1px solid #7F7F7F; border-top-left-radius: 25px; border-bottom-left-radius: 25px"
                   v-on:click="right = false ; left = true"
                   class="ligt_grey_custom--text"
                    >
                   
                <v-icon> notes</v-icon>
                <span> Abstract </span>
            </v-btn>
            <v-btn flat
                   style="border: 1px solid #7F7F7F; border-top-right-radius: 25px; border-bottom-right-radius: 25px"
                   v-on:click="right = true ; left = false"
                  class="pink_custom white--text"> 
                <v-icon> list </v-icon>
                <span> Articles </span>
            </v-btn>
        </v-flex>
    </v-layout>
        <v-alert
      :value="alert"
      type="success"
      transition="scale-transition"
    >
      Article ajouté
    </v-alert>
 

    <v-layout>
        <div style="text-align:justify" v-if="left" > {{ abstract }}
        </div>
        
    </v-layout>
    <!-- Articles est true -->
    <v-layout v-if="right" column>
        <v-flex>
        <h4>This State Of Art is in mode"{{contribution}}"</h4>
        </v-flex>

        <v-flex mt-5>
        <v-list>
            
            <v-list-tile v-for="article in listArticle" :key="article.ref" class="mb-2">
                <v-layout justify-space-between column>
                        <v-flex>
                            <v-layout row align-start justify-space-between>
                    
                                <v-flex xs11 class="light_grey_background"  mt-1 style="border:1px solid black">
                                    <v-layout column class="light_grey_background">
                                        <div v-if="article.visible==1"> 

                                             
                                        <v-flex>
                                            <span>  {{article.title}}  </span>
                                        </v-flex>
                                        <v-flex>
                                            <span >  {{article.date}} -   {{article.author}}  </span>
                                        </v-flex>
                                        </div> 
                                    </v-layout>
                                </v-flex>
                                <div v-if="article.visible==1"> 
                              <!--  <v-flex xs1>
                                    <v-btn large flat class="pink_custom" style="border-radius: 5px;"> 
                                        <v-icon class="white--text"> filter_none </v-icon>
                                    </v-btn> 
                                </v-flex> -->
                                </div>
                            </v-layout>
                        </v-flex>
                </v-layout>
            </v-list-tile>
            <h4 v-if="listArticle[0].stateOfArt.user.username==accessUsername" class="pink_custom--text"> These articles must be accepted or refused </h4>
            <v-list-tile v-for="article in listArticle" :key="article.ref" class="mb-2">
                <v-layout justify-space-between column> 
                        <v-flex mt-2>
                        
                         <div v-if="article.visible==0 && article.stateOfArt.user.username==accessUsername" >

                            
                            <!-- <h5>Articles rajoutés non acceptés:</h5>--->
                            <v-layout align-start justify-space-between row mt-2  >
                                
                               
                                <v-flex class="middle_pink white--text " xs10>
                                    <v-flex ml-2 >
                                            <span> {{article.title}}  </span>
                                        </v-flex>
                                        <v-flex ml-2>
                                            <span > {{article.date}} -   {{article.author}}  </span>
                                        </v-flex>
                                </v-flex>
                                <v-flex xs1 pl-5>
                                    
                                    <v-checkbox v-model="selected" color="pink_custom"   v-on:change="add_id_articles(article.id)"></v-checkbox>
                                    <!--<v-btn large flat class="pink_custom" style="border-radius: 5px;"> 
                                        <v-icon class="white--text"> filter_none </v-icon>
                                    </v-btn>-->
                                    
                                </v-flex>
                                   
                            </v-layout>
                             </div>
                    </v-flex>

                </v-layout>

            </v-list-tile>
            <p v-if="a_least_one==false">No article to accept/refuse</p>
        </v-list>
        </v-flex>
         
    </v-layout>
    
 <div v-if="accessUsername==id_user && right">      
         <v-btn round depressed @click="save_articles" color="pink_custom white--text">Save New Articles</v-btn>
    </div>
    
    <!--Add new articles-->

    <v-flex>
            <div v-for="(val, index) in title_art" :key="(val, index)">

                <v-flex xs8>
                    <v-card class=" mt-3 pa-2">
                        <v-card-actions>
                            <h4>Article N° {{index+1}}</h4>
                            <v-btn icon class="white pink_custom--text"
                                    style="margin-bottom: 25px; margin-left: auto;"
                                    @click="remove_one(index)">
                                    <v-icon>delete</v-icon>
                                </v-btn>
                        </v-card-actions>
                        <v-layout align-start justify-space-between row >
                                <v-flex mt-1 xs1 >
                                    <h4 class="font-weight-regular"> Title : </h4>
                                </v-flex>
                                <v-flex xs10 >
                                    <v-text-field hide-details v-model="val.value" flat solo background-color="fields_grey"> </v-text-field>
                                </v-flex>
                        </v-layout>
                        <v-flex mt-3 >
                            <v-layout align-start justify-space-between row>
                                    <v-flex mt-1 xs1>
                                        <h4 class="font-weight-regular"> Author : </h4>
                                    </v-flex>
                                    <v-flex xs10>
                                        <v-text-field hide-details v-model="author_art[index].value" flat solo background-color="fields_grey"> </v-text-field>
                                    </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex ml-2 mt-3>
                            <v-layout align-start justify-space-between row>
                                    <v-flex mt-1 xs1>
                                        <h4 class="font-weight-regular"> Date : </h4>
                                    </v-flex>
                                    <v-flex ml-3 xs3>
                                        <v-text-field hint="dd/mm/yyyy" v-model="date_art[index].value" flat solo background-color="fields_grey"> </v-text-field>
                                    </v-flex>
                                    <v-flex xs7>
                                    </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex mt-3>
                            <v-layout align-start justify-space-between row>
                                <v-flex mt-1 xs2 wrap>
                                    <h4 class="font-weight-regular"> keywords : </h4>
                                
                                </v-flex>
                                <v-flex xs9>
                                    <v-text-field @input="switch_button" hint="Please separate the keywords by commas" v-model="tags_art[index].value" flat solo background-color="fields_grey"> </v-text-field>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        
                </v-card>
             </v-flex>
            </div>
        </v-flex>
        <div v-if="right && (contribution == 'Free' || contribution =='Approval Required') && accessToken">  
        <v-btn round depressed @click="addFind" color="pink_custom white--text">Add New Article</v-btn>
    </div>
    <v-flex v-if="left == false" xs2>
                        <div v-if="title_art.length>0">
                <!--Permet de dériver les mots clés de l'état de l'art en fonction des mots clés de l'article-->
                
                        <v-btn @click="derive" round flat large class="pink_custom white--text">
                            Save articles
                        </v-btn>
                    
                        </div>
                        
                    </v-flex>
</v-layout>
</template>

<script>
 import { mapState, mapActions } from 'vuex';
import { constants } from 'crypto';
import { access } from 'fs';
import router from '../router'
import axios from 'axios'
export default {
    data: () => ({
        at_least_one:false,
        doubleSpace: "  ",
        alert:false, 
        back:'',
             //Data for articles
         author_art:[],
         title_art: [],
         date_art: [],
         tags_art: [],
         terms_add:null,
         authorId: '',
         idSOA: "",
         idUser: '',
         terms:[],
            id_articles_saved:[],
            id_user:"",
            accessUsername:localStorage.getItem('accessUsername'),
                titre : 'This State Of Art does not exist', 
                 author: '', 
                 creaDate:'',
                 establishment: '', 
                 lastModDate : '/' ,
                 abstract:'',
                 contribution:'',
                 nbarticle:'',
            coAuthor: [
                 ], 
            left: true,
            right: false,
            favorite: false,
            listArticle: [
            ]
        
    }),
     methods:{
    
     getArticles: function(id) {
           axios({
        method: 'GET',
        // 'http://localhost:8080/StateOfArts/'+id.toString() +"/articles"

        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +"/articles"
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +"/articles"
      }).then(response => {
          this.nbarticle = 0
          this.listArticle = response.data;
     console.log(response.data);
          for (const key in response.data) {
              if (response.data[key].visible == 1) {
                  this.nbarticle ++
                  
              }
          }
          //this.nbarticle=response.data.length;

      })
     },
     add_id_articles: function(id){
         if(!this.id_articles_saved.includes(id)){
            this.id_articles_saved.push(id);
         }else{
             for (const index in this.id_articles_saved) {
                 if (this.id_articles_saved[index]==id){
                        this.id_articles_saved.splice(index,1);
                 }
             }
             
         }
     }
     ,
     save_articles: function(){
         let urlParams = new URLSearchParams(window.location.search);
        let id = urlParams.get('id');
         for (const key in this.id_articles_saved) {
              axios({
            method: 'PUT',

            // http://localhost:8080/StateOfArts/
            //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
            url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()+"/articles/"+ this.id_articles_saved[key],

            headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },
            data: {visible:1}
        }).then(response => {
        })
         }
        
     },
      addFind: function () {
      this.addFind_author();
      this.addFind_title();
      this.addFind_date();
      this.addFind_tags();
    },
        addFind_author: function () {
      this.author_art.push({ value: '' });
    },
     addFind_title: function () {
      this.title_art.push({ value: '' });
    },
     addFind_date: function () {
      this.date_art.push({ value: '' });
    },
     addFind_tags: function () {
      this.tags_art.push({ value: '' });
      this.button= true;
    },
    
    remove_one: function(index){
        //this.author_art.splice(index,1);
        this.remove_author(index);
        this.remove_title(index);
        this.remove_date(index);
        this.remove_tags(index);
       
    },
    remove_author: function(index){
        this.author_art.splice(index,1);
    },
    remove_title: function(index){
        this.title_art.splice(index,1);
    },
    remove_date: function(index){
        this.date_art.splice(index,1);
    },
    remove_tags: function(index){
        this.tags_art.splice(index,1);
        this.button= true;
    },
    save: function(){
        var all_tags = [];
        var transit;
        for (const key in this.tags_art) {
            transit =this.tags_art[key].value.split(",")
            var all_tags = $.merge(all_tags, transit);
        }
        return all_tags;
    },
     derive({ commit }) {
        let urlParams = new URLSearchParams(window.location.search);
        let id = urlParams.get('id');
        let id2 = urlParams.get('user');
        axios({
        method: 'GET',

        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +"/articles"
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +"/articles"
      }).then(response => {
          this.listArticle = response.data;
          
      })
      var old_tags= [];
      for (const key in this.listArticle) {
          for (const key2 in this.listArticle[key].tags) {
              old_tags.push(this.listArticle[key].tags[key2].tag)
             
          }
        
      }
    var new_tags = this.save();
    var all_tags = new_tags.concat(old_tags)
    if(all_tags[0] !=""){
        this.button = false;
    }
      // http://localhost:8080/Derive
      // 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Derive'

      axios.post('http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Derive', {

        derive: all_tags
      })
      .then(response => {
        this.terms.splice(0,this.terms.length)
        var i = 0
        this.terms_add = response.data;
        for (const key in response.data) {
            if(i<5){
                this.terms.push ({label: response.data[i].name + " (" + (response.data[i].value*100) +"%)" })
                i++
            }
           
        }
        //Modify value of tags for SOA
        axios({
        method: 'PUT',

        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString(),
        headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
        data: {tags:this.terms_add}
    })
        // Add articles dans la BD
        var urls = "http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/"+id.toString()+"/articles"
         
     for(var i = 0 ;i<this.author_art.length;i++){
         var tab_tags = []
         
        tab_tags = this.tags_art[i].value.split(",");
         
             
           var data_article = {
            author: this.author_art[i].value ,
            title: this.title_art[i].value,
            date: this.date_art[i].value,
            tags: tab_tags
        };
         axios({
        method: 'POST',
        url: urls,
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
        data: data_article
      }).then(response => {
          return response.data;         
      })
      
     }
        
      }).catch(error => {
      })
        // refresh la page pour l'afficher
        self.alert = true;
        setTimeout(function(){
        
        router.go()
         },2000)
        
    }
 },
     created(){
        let urlParams = new URLSearchParams(window.location.search);
        let id = urlParams.get('id');
        let key_search = urlParams.get('tags')

        this.redirect_tags = key_search;
        this.back = "search?tags="+ key_search;

         axios({
        method: 'GET',
        // http://localhost:8080/StateOfArts/
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString()
      }).then(response => {

          this.id_user = response.data.user.username
          this.creaDate = response.data.date;
          this.establishment =response.data.user.establishment;
          this.titre =response.data.title;
           this.abstract =response.data.theabstract;
           this.author= (response.data.user.lastname) + " " + (response.data.user.firstname)
           this.coAuthor = response.data.authors;
           this.contribution = response.data.contribution;
          this.getArticles(id);
          this.idSOA = id;
          
          this.authorId = response.data.user.id;
      })
        //Get last modify date
       axios({
        method: 'GET',
        //'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +'history'
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/'+id.toString() +'/history'
      }).then(response => {
          var string_date='';
         var tab_date= response.data[response.data.length-1]
         for (const key in tab_date.dateTime.splice(3)) {
             if(key == 0){
                string_date = tab_date.dateTime[key]  
             }else{
                 string_date = tab_date.dateTime[key] + '/'+string_date 
             }

      }
      
      

      this.lastModDate = string_date
          
      })
     

         
     },
     computed: {
      ...mapState([
        'loggingIn',
        'loginError',
        'accessToken',
        'accessUsername'
      ])
     
    }
    
}
</script>

