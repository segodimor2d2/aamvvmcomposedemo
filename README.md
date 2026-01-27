


# 🧠 Visão geral (antes do detalhe)

O que você construiu é isso:

```bash
Button (UI)
  ↓ clique
ViewModel.changeMessage()
  ↓ altera
MutableStateFlow
  ↓ emite novo valor
collectAsState()
  ↓ avisa o Compose
Recomposição
  ↓
Text atualizado
```
--- 

```kotlin
val state = viewModel.message.collectAsState()
val message = state.value
======
val message by viewModel.message.collectAsState()
```
