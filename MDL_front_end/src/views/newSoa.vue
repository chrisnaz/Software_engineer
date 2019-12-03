<template>
    <v-layout column mt-5 ml-5 mr-5>
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> Create New State of the Art </h1>
            </v-flex>
        </v-layout>

    <!--Show SOA-->
        <v-layout align-start row>
            <!-- Title, Date, Author -->
            <v-flex xs3 mr-5>
                <v-layout fill-height column mt-5>
                    <v-flex>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Title : </h4>
                            </v-flex>
                            <v-flex xs8>
                                <v-text-field hide-details v-model="title" flat solo background-color="fields_grey"> </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3>
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Date : </h4>
                            </v-flex>
                            <v-flex  xs8 >

                                <v-text-field append-icon="event" v-model="date" @click="dialog=!dialog"  color="pink_custom" hide-details flat solo background-color="fields_grey"></v-text-field>

                            </v-flex>
                            <v-layout row wrap>
                                <v-flex xs12 sm6>
                                <v-date-picker v-model="date" v-if="dialog" style="position: fixed; z-index: 10; border-radius: 5px;" color="pink_custom">
                                    <v-spacer></v-spacer>
                                    <v-btn flat color="pink_custom" @click="()=>{dialog = false; date = new Date().toISOString().substr(0, 10)}">Cancel</v-btn>
                                    <v-btn flat color="pink_custom" @click="dialog=false">OK</v-btn>
                                </v-date-picker>
                                </v-flex>
                            </v-layout>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
            <!-- contribution, co-author -->
            <v-flex xs4 ml-5 >
                <v-layout fill-height column mt-5>
                    <v-flex  mb-2 >
                        <v-layout align-start justify-space-between row>
                            <v-flex  xs4 >
                                <h4 class="font-weight-regular"> Contribution : </h4>
                            </v-flex>
                            <v-flex  xs8>
                                <v-menu full-width>
                                    <v-btn flat slot="activator" class="fields_grey" block>
                                            <span> {{status}} </span>
                                            <v-icon right> expand_more </v-icon>
                                    </v-btn>
                                    <v-list>
                                        <v-list-tile v-for="item in items" :key="item.title" router @click=" status = item.title" > 
                                        <v-list-tile-title> {{ item.title}} </v-list-tile-title>
                                        </v-list-tile>
                                    </v-list>
                                </v-menu>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex mt-3 >
                        <v-layout align-start justify-space-between row>
                            <v-flex xs4 >
                                <h4 class="font-weight-regular"> Co-authors : </h4>
                            </v-flex>

                            <v-flex mt-1 xs8 >
                                <v-text-field  v-model="coauteur" flat solo background-color="fields_grey" hint="Please separate the authors by commas (Always firstname first)"> </v-text-field>

                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>   
        </v-layout>
        <!-- abstract -->
        <v-layout align-start justify-start row mt-5>
                <v-flex xs1>
                    <h4 class="font-weight-regular"> Abstract : </h4>
                </v-flex>
                <v-flex xs7>
                    <v-textarea style="border-radius: 5px; z-index: 1;"
                                class="fields_grey"
                                outline
                                hide-details
                                color="pink_custom"
                                label="Summarize you state of the art"
                                v-model="abstract"
                                auto-grow>
                    </v-textarea>
                </v-flex>
        </v-layout>
        <v-layout align-start justify-start row mt-5>

            <v-flex xs1>
                    <h4 class="font-weight-regular"> Articles : </h4>
                </v-flex>
        </v-layout>
        <!--Show Article-->
        <!--Affichage input articles-Ne s'affiche que si on appuie sur le boutton + via la method addFind-->
        <v-flex>
            <div v-for="(val, index) in title_art" :key="(val,index)">
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
                                    <h5 class="font-weight-regular"> Title : </h5>
                                </v-flex>
                                <v-flex xs10 >
                                    <v-text-field hide-details v-model="val.value" flat solo background-color="fields_grey"> </v-text-field>
                                </v-flex>
                        </v-layout>
                        <v-flex mt-3 >
                            <v-layout align-start justify-space-between row>
                                    <v-flex mt-1 xs1>
                                        <h5 class="font-weight-regular"> Author : </h5>
                                    </v-flex>
                                    <v-flex xs10>
                                        <v-text-field hide-details v-model="author_art[index].value" flat solo background-color="fields_grey"> </v-text-field>
                                    </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex ml-2 mt-3>
                            <v-layout align-start justify-space-between row>
                                    <v-flex mt-1 xs1>
                                        <h5 class="font-weight-regular"> Date : </h5>
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
                                    <h5 class="font-weight-regular"> keywords : </h5>
                                
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
         <!--Permet d'ajouter un nouvel article-->
        
        <v-layout mt-2 row>
        <v-flex xs8>
            <v-layout row justify-end>
                <v-flex xs1>
                    <v-btn @click="addFind" fab round flat class="pink_custom white--text"> 
                        <v-icon> add_box </v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
        </v-flex>
        </v-layout>
        
        
        <v-layout mt-2 row>
            <v-flex xs8>
                <v-layout row justify-center>
                    <v-flex xs2>
                        <div v-if="title_art.length>0">
                <!--Permet de dériver les mots clés de l'état de l'art en fonction des mots clés de l'article-->
                
                        <v-btn @click="derive" round flat large class="pink_custom white--text">
                            Save articles
                        </v-btn>
                    
                        </div>
                        
                    </v-flex>
                    <!--<v-flex xs2>
                        <v-btn round flat large outline to="/viewclassificationscheme">
                            <span> View Classification Scheme </span>
                        </v-btn>
                    </v-flex>-->
                   
                    
                </v-layout>
            </v-flex>
        </v-layout>

        <v-layout align-start justify-start row mt-5>
            <v-flex xs1>
                <!-- alignement- Inutilisé pour le moment-->
            </v-flex>
            <!--<v-flex xs6 >
                <v-btn flat 
                    large
                    style="border: 1px solid #7F7F7F"
                    round
                    class="ligt_grey_custom--text mr-5"
                    to="/classificationscheme">
                    
                    <span> Create Classification Scheme </span>
                </v-btn>
                <v-btn flat
                    large
                    style="border: 1px solid #7F7F7F;"
                    round
                    class="ligt_grey_custom--text"> 
                    
                    <span> View Classification Scheme </span>
                </v-btn>
            </v-flex>-->
        </v-layout>

        <v-layout align-start justify-start row mt-5>
            <!-- associated terms- Affiche les termes associés du SOA -->
            <v-flex xs3>
                <h4 class="font-weight-regular mt-3" > Associated terms : </h4>
            </v-flex>
            <v-flex mt-1 xs9>
                
                    <div v-if="terms.length==0">
                        <h5 class="font-weight-regular" >Please add the articles and save them to get the associated terms</h5>
                    </div>
                    
                    <v-btn class="fields_grey black--text"
                           
                           flat 
                           round  
                           large 
                           
                           v-for="term in terms" 
                           :key="term.label"
                           v-model="term.model">
                           {{term.label}}
                    </v-btn>
                    
                
            </v-flex>
        </v-layout>
        <hr class="mt-5 mb-5">

        <v-layout align-end justify-end row mb-5>
            <v-flex xs1 mr-5>
                <v-btn outline flat round large to="/"> Cancel
                </v-btn>
            </v-flex>
            <!--On ne peut l'utiliser que si on a bien sauvegarder les articles (via le bouton save articles)-->
            <v-flex xs1>
                <v-btn @click="createArticle" :disabled="button" flat round large outline class="pink_custom--text" >Create
                </v-btn>
            </v-flex>


        </v-layout>
    

    </v-layout>
</template>

<script>

import { mapState, mapActions } from 'vuex';
//import { constants } from 'crypto';
import axios from 'axios'
import { setTimeout } from 'timers';
import router from '../router'

export default {
     data (){
     return {
         //Data for articles
         author_art:[],
         title_art: [],
         date_art: [],
         tags_art: [],
         //others
         dialog: false,
         dialog2: true,
         button:true,

         //SOA
         id_soa:0,
         title: '',
         date: new Date().toISOString().substr(0, 10),
         good_date: '',
         author:'',
         abstract: '',
         contribution:'',
         coauteur:'',
         clicked : false,
        terms:[
        ],
        terms_add:null,
        

         items: [
        { title: 'Free' },
        { title: 'Approval Required' },
        //{ title: 'Free' },
        ]

        ,
         status: 'Approval Required',
         
         
     }
    
     },
     //Récupére les informations de connexion de l'utilisateur
     computed:{
      ...mapState([
        'loggingIn',
        'loginError',
        'accessToken'
      ]),
      colorbutton: function() {
             return {
                 "pink_custom white--text" : this.clicked == true,
                 "fields_grey black--text": this.clicked == false
                }
            }
     },
     methods:{
         change: function(id){
            
            this.id_soa= id
           
        },
        change_format(){
            this.good_date = this.date.slice(8,10) + "/" + this.date.slice(5,7) + "/" + this.date.slice(0,4);
        },
       //switch the button to add a new SOA on/off
        switch_button: function(){

            this.button =true;
        },
        //add a new box to add an article
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
    derive(/*{ commit }*/) {
    
    var all_tags = this.save();
    
    if(all_tags[0] !=""){
        this.button = false;
    }

        //Get Tags compare to what the user add in the articles fields
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
        
      })
      .catch(error => {})

    },
    createSOA(/*{commit}*/){
        //Prepare data to add SOA
        var nb_articles= this.author_art.length;
        var therole;
        // less than ["10", "20", "30", "40","50","100","200"] 
        if(nb_articles <10){
            therole = "10";

        }else if (nb_articles <20) {
            therole = "20";

        }else if (nb_articles <30) {
            therole = "30";

        }else if (nb_articles <40) {
            therole = "40";

        }else if (nb_articles <50) {
            therole = "50";

        }else if (nb_articles <100) {
            therole = "100";

        }else{
            therole = "200";

        }
        
        var co_authors = this.coauteur.split(',');
        
        var split_named=[];
        var transit_split=[];
        
        for (const key in co_authors) {
            transit_split = co_authors[key].trim().split(" ");
            split_named.push({firstname:transit_split[0],lastname:transit_split[1]})
           
        }


        this.change_format();        

        for (const key in this.terms_add) {
            this.terms_add[key].name = this.terms_add[key].name.replace(" ","");
        }


        var datas= {

        title: this.title,
        date: this.good_date,
        theabstract: this.abstract,
        tags: this.terms_add,
        contribution: this.status,
        authors: split_named,
        role: therole


      }
      //Add SOA in DB
      axios({
        method: 'GET',
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/UsersName/'+localStorage.getItem('accessUsername').toString()
      }).then(response => {
          this.id_user = response.data.id;
      })
       var self = this;
       setTimeout(function(){
      axios({
        method: 'POST',
        url: 'http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Users/'+self.id_user.toString()+'/StateOfArts',
        headers: {
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },
        data: datas
      }).then(response => {
          
         self.change(response.data);
        //this.id_soa = response.data;

      })
        },1000)
      

    },
    createArticle({commit}){
    this.button= true;
    this.createSOA({commit});

     
   
    //sauvegarde du context pour l'utiliser dans une fonction plus bas
    self = this;
    //On laisse le temps d'écrire le SOA dans la BD avant d'écrire les articles
    setTimeout(function(){
        // http://localhost:8080/StateOfArts/
        //"http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/"+self.id_soa.toString()+"/articles"
     var urls = "http://mdl-std05.info.fundp.ac.be:8181/mdl_web/StateOfArts/"+self.id_soa.toString()+"/articles"
         
    var self2 = self;
     for(var i =0 ;i<self2.author_art.length;i++){
         var tab_tags = []
         
        tab_tags = self2.tags_art[i].value.split(",");
         
             
           var data_article = {

            author: self2.author_art[i].value ,
            title: self2.title_art[i].value,
            date: self2.date_art[i].value,
            tags: tab_tags,
            visible: 1
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
     }, 3000);
    //Wait to redirect that everything has been added
    setTimeout(function(){
        
       router.push('/Soa?id=' + self.id_soa);
    },4000)
     
    }

     },
 
     
    }


</script>
