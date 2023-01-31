<template>
<div v-if="this.actions.length > 0">
  <table class="uk-table uk-table-striped uk-table-hover">
    <thead>
    <tr>
      <th width="5%">Czas</th>
      <th width="5%">Typ</th>
      <th width="5%"></th>
      <th width="5%"></th>
      <th width="75%">Akcja</th>
      <th width="10%" v-if="User !== null">Usuń</th>
    </tr>
    </thead>

    <tbody v-for="action in actions" :key="action.id" id="tlh-tabelka-akcje">
    <SingleActionView
        :action="action"
        @incrementKey="incrementKey"
    />

    </tbody>
  </table>
</div>
  <div v-else>

  </div>

</template>

<script>
import axios from "axios";
import SingleActionView from "@/views/Match/SingleActionView";
import {mapGetters} from "vuex";
export default {
  name: "MatchActionsView",
  components: {SingleActionView},
  props: ['matchId'],
  emits: ['incrementKey'],
  data(){
    return {
      actions: [],
    }
  },
  methods: {
    incrementKey(context){
      this.$emit('incrementKey',context)
    }
  },
  computed: {
    ...mapGetters({User: "StateUser"})
  },
  created() {
    axios.get("http://localhost:8080/api/matches/"+this.matchId+"/actions")
        .then(response => {
          this.actions = response.data.actions
          this.actions = this.actions.sort(function (obj1, obj2){
            return obj2.totalTimeInSeconds - obj1.totalTimeInSeconds;
          })

        })
  }
}
</script>

<style scoped>
.uk-table-striped > tr:nth-of-type(odd) {
  background: #8debff;
}
.uk-table-hover > *  {
  background: #8debff;
}

#tlh-tabelka-akcje > *:hover {
  background: #a6efff;
}
#tlh-tabelka-akcje > tr > td > img {
  height: 100px;
}
</style>