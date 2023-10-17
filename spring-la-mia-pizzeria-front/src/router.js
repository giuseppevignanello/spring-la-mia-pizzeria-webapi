import { createRouter, createWebHistory } from "vue-router";
import PizzasApp from "./components/PizzasApp.vue";
import CreatePizza from "./components/CreatePizza.vue";
import EditPizza from "./components/EditPizza.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "home", component: PizzasApp },
    { path: "/create", name: "create", component: CreatePizza },
    { path: "/edit/:id", name: "edit", component: EditPizza },
  ],
});

export { router };
