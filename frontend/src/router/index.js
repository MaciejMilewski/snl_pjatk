import { createRouter, createWebHashHistory } from 'vue-router'
import store from "@/store";
import StartComponent from "@/components/Start/StartComponent";
import PlayerForm from "@/components/Players/Player/PlayerForm";
import LoginComponent from "@/components/Login/LoginForm";
import PlayersComponent from "@/components/Players/PlayersComponent";
import Logout from "@/components/Logout/Logout";
import SingleArticleView from "@/views/News/Articles/SingleArticle";
import SinglePlayerView from "@/views/Player/SinglePlayer";
import EditPlayer from "@/components/Players/Player/EditPlayer";
import EditUser from "@/views/Users/EditUser";
import EditArticle from "@/components/Article/EditArticle";
import Panel from "@/components/Panel/Panel";
import TeamsList from "@/views/Teams/TeamsList";
import SingleTeamView from "@/views/Teams/Team/SingleTeamView";
import EditTeam from "@/components/Team/EditTeam";
import MatchComponent from "@/components/Match/MatchComponent.vue";
import LeaguesListView from "@/views/Leagues/LeaguesListView";
import ContactView from "@/views/Contact/ContactView";
import SingleLeagueView from "@/views/Leagues/SingleLeagueView";
const routes = [
  {
    path: '/panel',
    name: 'panel',
    component: Panel,
    meta: {requiresAuth: true},
  },
  {
    path: '/wyloguj-sie',
    name: 'wylogowywanie',
    component: Logout,
  },
  {
    path: '/zaloguj-sie',
    name: 'logowanie',
    component: LoginComponent

  },
  {
    path: '/mecz/:id',
    name: 'pojedynczy-mecz',
    props: true
  },
  {
    path: '/',
    name: 'aktualnosci',
    component: StartComponent
  },
  {
    path: '/ligi',
    name: 'ligi',
    component: LeaguesListView
  },
  {
    path: '/zespoly',
    name: 'zespoly',
    component: TeamsList
  },
  {
    path: '/single-league/:id',
    name: 'liga-pojedyncza',
    component: SingleLeagueView,
    props: true,
  },
  {
    path: '/zespol/:id/:league',
    name: 'zespol-pojedynczy',
    component: SingleTeamView,
    props: true,
  },
  {
    path: '/edytuj-zespol/:id',
    name: 'edytuj-zespół',
    component: EditTeam,
    props: true,
    meta: {requiresAuth: true},
  },
  {
    path: '/kontakt',
    name: 'kontakt',
    component: ContactView,
  },
  {
    path: '/artykul/:id',
    name: 'artykul-pojedynczy',
    component: SingleArticleView,
    props: true,
  },
  {
    path: '/edytuj-artykul/:id',
    name: 'edytuj-artykuł',
    component: EditArticle,
    props: true,
    meta: {requiresAuth: true},
  },
  {
    path: '/zawodnicy',
    name: 'zawodnicy',
    component: PlayersComponent
  },
  {
    path: '/zawodnik/:id',
    name: 'zawodnik',
    component: SinglePlayerView,
    props: true,
  },
  {
    path: '/edytuj-zawodnika/:id',
    name: 'edytuj-zawodnika',
    component: EditPlayer,
    props: true,
    meta: {requiresAuth: true},
  },
  {
    path: '/edytuj-uzytkownika/:id',
    name: 'edytuj-uzytkownika',
    component: EditUser,
    props: true,
    meta: {requiresAuth: true},
  },
  {
    path: '/mecz-widok/:id',
    name: 'mecz',
    component: MatchComponent,
    props: true,
  },
  {
    path: '/dodawanie-zawodnika',
    name: 'dodawanie-zawodnika',
    component: PlayerForm,
    meta: {requiresAuth: true},
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)){
    if(store.getters.isAuthenticated){
      next()
      return
    }
    //Brak autoryzacji - redirect na stronę główną /
    next('/')
  } else {
    next()
  }
})

export default router
