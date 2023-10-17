<template>
  <div>
    <h1>Pizze</h1>
    <h3>Ricerca Pizze</h3>
    <form @submit.prevent="searchPizzas">
      <input type="text" v-model="searchTerm" placeholder="Cerca pizze...">
      <button type="submit">Cerca</button>
    </form>
    <div>
      <div>
        <a href="#">
          <router-link :to="{ name: 'create' }">
            Create
          </router-link>
        </a>
      </div>
      <div v-if="search">
        <ul v-if="searchResults.length > 0">
          <li v-for=" pizza  in  searchResults ">
            <p> {{ pizza.name }} </p>
            <p> {{ pizza.description }}</p>
            <p> $ {{ pizza.price }}</p>
            <div class="buttons">
              <router-link :to="{ name: 'edit', params: { id: pizza.id } }">
                <button>
                  Edit
                </button>
              </router-link>
              <button @click="pizzaDelete(pizza.id)">Delete</button>
            </div>
          </li>
        </ul>
        <div v-else>
          Nessun risultato
        </div>
      </div>
      <ul v-if="!search">
        <li v-for=" pizza  in  pizzas ">
          <p> {{ pizza.name }} </p>
          <p> {{ pizza.description }}</p>
          <p> $ {{ pizza.price }}</p>
          <div class="buttons">
            <router-link :to="{ name: 'edit', params: { id: pizza.id } }">
              <button>
                Edit
              </button>
            </router-link>
            <button @click="pizzaDelete(pizza.id)">Delete</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios';
const apiUrl = "http://localhost:8080/api/v1.0";
const pizzas = ref(null);
const search = ref(false);
const searchTerm = ref('');
const searchResults = ref([]);

function searchPizzas() {
  axios.get(`${apiUrl}/filter/${searchTerm.value}`)
    .then((response) => {
      search.value = true;
      searchResults.value = response.data;
    })
    .catch((error) => {
      console.error(error.message);
    });
}

function pizzaDelete(id) {
  axios.delete(apiUrl + '/' + id)
    .then(response => {
      getAll();
    })
    .catch((error) => {
      console.error(error.message);
    })

}

function getAll() {
  axios.get(apiUrl)
    .then((response) => {
      pizzas.value = response.data;
    })
    .catch((error) => {
      console.error(error.message);
    })
}

onMounted(() => {
  getAll();
}
)
</script>

<style lang="scss" scoped></style>