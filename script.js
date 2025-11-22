// script.js

// --- 1. Funções Comuns para Modals e Formatação ---

let itemToDelete = null; 

function setupModal(modalId, openBtnId, cancelBtnId, confirmBtnId, onConfirm) {
    const modal = document.getElementById(modalId);
    const openBtn = document.getElementById(openBtnId);
    const cancelBtn = document.getElementById(cancelBtnId);
    const confirmBtn = document.getElementById(confirmBtnId);

    const openModal = () => modal && modal.classList.add('active');
    const closeModal = () => modal && modal.classList.remove('active');

    if (openBtn) {
        openBtn.addEventListener('click', () => {
            if (modalId === 'modalAdicionarValorDespesa') {
                updateExpenseGoalOptions();
            } else if (modalId === 'modalAdicionarValorInvestimento') {
                updateInvestmentOptions(); 
            }
            openModal();
        });
    }

    if (cancelBtn) {
        cancelBtn.addEventListener('click', closeModal);
    }
    
    if (modal) {
        modal.addEventListener('click', (e) => {
            if (e.target === modal) {
                closeModal();
            }
        });
    }

    if (confirmBtn) {
        confirmBtn.addEventListener('click', () => {
            if (onConfirm()) {
                closeModal();
            }
        });
    }
    
    return { openModal, closeModal };
}

/**
 * Formata um número para a moeda Brasileira (R$).
 */
function formatCurrency(value) {
    const numericValue = parseFloat(value);
    if (isNaN(numericValue)) return '0,00';
    return numericValue.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

/**
 * Extrai um valor numérico (float) de uma string formatada em R$.
 */
function parseCurrency(text) {
    if (!text) return 0;
    let cleanedText = text.replace(/[^0-9,-]/g, ''); 
    cleanedText = cleanedText.replace(/\./g, '').replace(/,/g, '.');
    return parseFloat(cleanedText) || 0;
}


// --- 2. Funções de Despesas (expenses.html) ---

function calculateTotalExpensesAndDisplay() {
    const allGoals = document.querySelectorAll('#expenseGoalsContainer .goal-item');
    let total = 0;
    
    allGoals.forEach(goal => {
        const current = parseFloat(goal.dataset.current) || 0;
        total += current;
    });

    const totalDisplay = document.getElementById('total-expenses-display');
    if (totalDisplay) {
        const formattedTotal = formatCurrency(Math.abs(total));
        const sign = total < 0 ? '-' : '';
        totalDisplay.textContent = `${sign}R$ ${formattedTotal}`;
    }
}

function renderGoalDisplay(goalItem) {
    const currentExpense = parseFloat(goalItem.dataset.current) || 0;
    const limit = parseFloat(goalItem.dataset.limit) || 1; 
    const color = goalItem.dataset.color;
    
    const newPercentage = (Math.abs(currentExpense) / limit) * 100;
    
    const progressElement = goalItem.querySelector('.progress');
    
    if (progressElement) {
        progressElement.className = 'progress'; 
        progressElement.classList.add(`progress-${color}`);

        progressElement.style.width = `${Math.min(newPercentage, 100).toFixed(1)}%`;
        
        if (currentExpense > limit) {
             progressElement.classList.add('progress-red');
        } else {
             progressElement.classList.remove('progress-red');
        }
    }
    
    const goalValueDiv = goalItem.querySelector('.goal-value');
    if (goalValueDiv) {
         const currentText = formatCurrency(Math.abs(currentExpense));
         const sign = currentExpense < 0 ? '-' : '';

         goalValueDiv.innerHTML = `${sign}R$ <span class="current-expense-value">${currentText}</span> / R$ ${formatCurrency(limit)}`;
    }
}

function initializeGoalDisplay() {
    const allGoals = document.querySelectorAll('#expenseGoalsContainer .goal-item');
    allGoals.forEach(goal => {
        // Esta chamada garante que o JS renderize corretamente a barra, 
        // mesmo que o HTML inicial tenha valores estáticos.
        renderGoalDisplay(goal); 
    });
}

function updateExpenseGoal(categoryName, addedValue) {
    const goalItem = document.querySelector(`#expenseGoalsContainer .goal-item[data-category="${categoryName}"]`);
    if (!goalItem) return false;

    let currentExpense = parseFloat(goalItem.dataset.current) || 0;
    const newTotalExpense = currentExpense + addedValue;
    goalItem.dataset.current = newTotalExpense.toFixed(2);
    
    renderGoalDisplay(goalItem); 
    calculateTotalExpensesAndDisplay();
    
    return true;
}

/**
 * ATUALIZADO: A barra de progresso agora é totalmente renderizada pelo renderGoalDisplay.
 */
function createNewExpenseGoal(name, limit, color) {
    const container = document.getElementById('expenseGoalsContainer');
    if (!container) return;

    const newGoalElement = document.createElement('div');
    newGoalElement.classList.add('goal-item');
    newGoalElement.dataset.category = name;
    newGoalElement.dataset.limit = limit.toFixed(2);
    newGoalElement.dataset.current = "0.00";
    newGoalElement.dataset.color = color;
    newGoalElement.dataset.initialized = 'true';

    // *** MUDANÇA AQUI: Removendo o style="width" e o valor inicial do goal-value ***
    newGoalElement.innerHTML = `
        <div class="goal-header">
            <div class="goal-title-container">
                <div class="goal-title">${name}</div> 
                <button class="delete-button" data-type="expense" data-name="${name}">🗑️</button>
            </div>
            <div class="goal-value"></div> </div>
        <div class="progress-bar">
            <div class="progress progress-${color}"></div> </div>
    `;
    // ****************************************************************************

    container.appendChild(newGoalElement);
    renderGoalDisplay(newGoalElement); // <-- O renderGoalDisplay agora preenche o goal-value e define o width
    calculateTotalExpensesAndDisplay();
}

function deleteExpenseGoal(name) {
    const goalItem = document.querySelector(`#expenseGoalsContainer .goal-item[data-category="${name}"]`);
    if (goalItem) {
        goalItem.remove();
        calculateTotalExpensesAndDisplay();
        return true;
    }
    return false;
}

function updateExpenseGoalOptions() {
    const select = document.getElementById('categoriaAdicionarValor');
    if (!select) return;

    select.innerHTML = ''; 

    const allGoals = document.querySelectorAll('#expenseGoalsContainer .goal-item');
    
    allGoals.forEach(goal => {
        const categoryName = goal.dataset.category;
        if (categoryName) {
            const option = document.createElement('option');
            option.value = categoryName;
            option.textContent = categoryName;
            select.appendChild(option);
        }
    });
}


// --- 3. Funções de Investimentos (investments.html) ---

/**
 * Renderiza a exibição de um item de investimento (valor formatado e barra).
 */
function renderInvestmentItem(itemElement, totalPortfolio) {
    const value = parseFloat(itemElement.dataset.value) || 0;
    const color = itemElement.dataset.color || 'blue';

    const goalValueDiv = itemElement.querySelector('.goal-value');
    if (goalValueDiv) {
         goalValueDiv.textContent = `R$ ${formatCurrency(value)}`;
    }

    const progressElement = itemElement.querySelector('.progress');
    if (progressElement) {
        progressElement.className = 'progress'; 
        progressElement.classList.add(`progress-${color}`);

        const percentage = totalPortfolio > 0 ? (value / totalPortfolio) * 100 : 0;
        progressElement.style.width = `${percentage.toFixed(1)}%`;
    }
}

/**
 * Inicializa a carteira: lê valores estáticos do HTML e seta data-attributes.
 */
function initializeInvestmentPortfolio() {
    const container = document.getElementById('investmentsGoalsContainer');
    if (!container) return;
    
    const investmentItems = container.querySelectorAll('.goal-item');
    
    investmentItems.forEach(item => {
        if (!item.dataset.initialized) {
            const goalTitle = item.querySelector('.goal-title').textContent.trim();
            const staticValueText = item.querySelector('.goal-value').textContent;
            const staticValue = parseCurrency(staticValueText);
            
            const progressElement = item.querySelector('.progress');
            let color = 'blue';
            if (progressElement) {
                const classList = Array.from(progressElement.classList);
                const colorClass = classList.find(c => c.startsWith('progress-'));
                if (colorClass) {
                    color = colorClass.replace('progress-', '');
                }
            }

            item.dataset.title = goalTitle;
            item.dataset.value = staticValue.toFixed(2);
            item.dataset.color = color;
            item.dataset.initialized = 'true';
        }
    });
}

/**
 * Calcula o total da carteira e renderiza todos os itens (valores e barras).
 */
function calculateAndRenderAllInvestments() {
    let total = 0;
    const investmentItems = document.querySelectorAll('#investmentsGoalsContainer .goal-item');
    
    // 1. Calcular o novo total
    investmentItems.forEach(item => {
        total += parseFloat(item.dataset.value) || 0;
    });

    // 2. Atualizar o display do total
    const totalDisplay = document.querySelector('.main-content .performance-container p[style*="font-size: 24px"]');
    if (totalDisplay) {
        totalDisplay.textContent = `R$ ${formatCurrency(total)}`;
    }

    // 3. Renderizar cada item 
    investmentItems.forEach(item => {
        renderInvestmentItem(item, total);
    });

    return total;
}

/**
 * Atualiza o <select> no modal de Adicionar Saldo com todos os investimentos disponíveis.
 */
function updateInvestmentOptions() {
    const select = document.getElementById('investimentoSelect');
    if (!select) return;

    select.innerHTML = ''; 
    const allItems = document.querySelectorAll('#investmentsGoalsContainer .goal-item');
    
    allItems.forEach(item => {
        const title = item.dataset.title;
        const value = parseFloat(item.dataset.value) || 0;
        
        if (title) {
            const option = document.createElement('option');
            option.value = title;
            option.textContent = `${title} (R$ ${formatCurrency(value)})`;
            select.appendChild(option);
        }
    });
}

/**
 * Adiciona um novo item de investimento ao DOM.
 */
function createNewInvestmentItem(name, value, color) {
    const container = document.getElementById('investmentsGoalsContainer');
    if (!container) return;

    const newGoalElement = document.createElement('div');
    newGoalElement.classList.add('goal-item');
    newGoalElement.dataset.title = name;
    newGoalElement.dataset.value = value.toFixed(2);
    newGoalElement.dataset.color = color;
    newGoalElement.dataset.initialized = 'true';
    
    newGoalElement.innerHTML = `
        <div class="goal-header">
            <div class="goal-title-container">
                <div class="goal-title">${name}</div>
                <button class="delete-button" data-type="investment" data-name="${name}">🗑️</button>
            </div>
            <div class="goal-value">R$ ${formatCurrency(value)}</div>
        </div>
        <div class="progress-bar">
            <div class="progress progress-${color}" style="width: 0%"></div>
        </div>
    `;

    container.appendChild(newGoalElement);
    calculateAndRenderAllInvestments();
}

/**
 * Adiciona um valor a um investimento existente.
 */
function addInvestmentValue(title, addedValue) {
    const itemElement = document.querySelector(`#investmentsGoalsContainer .goal-item[data-title="${title}"]`);
    if (!itemElement) return false;

    let currentValue = parseFloat(itemElement.dataset.value) || 0;
    const newValue = currentValue + addedValue;
    itemElement.dataset.value = newValue.toFixed(2);
    
    calculateAndRenderAllInvestments();
    return true;
}

/**
 * Remove um investimento.
 */
function deleteInvestmentItem(title) {
    const itemElement = document.querySelector(`#investmentsGoalsContainer .goal-item[data-title="${title}"]`);
    if (itemElement) {
        itemElement.remove();
        calculateAndRenderAllInvestments();
        return true;
    }
    return false;
}


// --- 4. Inicialização e Listeners ---

document.addEventListener('DOMContentLoaded', () => {
    const currentPage = window.location.pathname.split('/').pop();

    // ======================================================================
    // 🗑️ LÓGICA DE EXCLUSÃO
    // ======================================================================
    const deleteModal = document.getElementById('deleteConfirmationModal');
    const itemToDeleteNameDisplay = document.getElementById('itemToDeleteName');
    const cancelDeleteBtn = document.getElementById('cancelDeleteBtn');
    const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
    
    const openDeleteModal = (name, type) => {
        itemToDelete = { name, type };
        itemToDeleteNameDisplay.textContent = name;
        deleteModal.classList.add('active');
    };

    const closeDeleteModal = () => {
        itemToDelete = null;
        deleteModal.classList.remove('active');
    };

    if (cancelDeleteBtn) cancelDeleteBtn.addEventListener('click', closeDeleteModal);
    
    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', () => {
            if (itemToDelete) {
                if (itemToDelete.type === 'expense') {
                    deleteExpenseGoal(itemToDelete.name);
                } else if (itemToDelete.type === 'investment') {
                    deleteInvestmentItem(itemToDelete.name);
                }
            }
            closeDeleteModal();
        });
    }

    document.addEventListener('click', (e) => {
        const target = e.target;
        if (target.classList.contains('delete-button')) {
            const name = target.dataset.name;
            const type = target.dataset.type;
            if (name && type) {
                openDeleteModal(name, type);
            }
        }
    });
    
    // ======================================================================
    // 💰 LÓGICA DA PÁGINA EXPENSES.HTML
    // ======================================================================
    if (currentPage.includes('expenses.html')) {
        initializeGoalDisplay();
        calculateTotalExpensesAndDisplay();

        // 1. Modal Nova Meta
        setupModal(
            'modalNovaMetaGasto', 
            'novaMetaGastoBtn', 
            'cancelarNovaMeta', 
            'confirmarNovaMeta',
            () => {
                const nome = document.getElementById('novaMetaNome').value.trim(); 
                const limite = parseFloat(document.getElementById('novaMetaLimite').value);
                const cor = document.getElementById('novaMetaCor').value;
                
                if (!nome || isNaN(limite) || limite <= 0) {
                    alert('Por favor, preencha o Nome da Meta e um Valor Limite válido (maior que zero).');
                    return false;
                }
                
                if (document.querySelector(`#expenseGoalsContainer .goal-item[data-category="${nome}"]`)) {
                     alert(`A meta de gasto "${nome}" já existe. Por favor, escolha outro nome.`);
                    return false;
                }

                createNewExpenseGoal(nome, limite, cor); 
                document.getElementById('novaMetaNome').value = '';
                document.getElementById('novaMetaLimite').value = '';
                
                return true;
            }
        );

        // 2. Modal Adicionar Lançamento
        setupModal(
            'modalAdicionarValorDespesa', 
            'adicionarValorDespesaBtn', 
            'cancelarAdicionarValor', 
            'confirmarAdicionarValor',
            () => {
                const categoria = document.getElementById('categoriaAdicionarValor').value; 
                const valorAdicionar = parseFloat(document.getElementById('valorAdicionarDespesa').value);

                if (!categoria || isNaN(valorAdicionar) || valorAdicionar === 0) { 
                    alert('Por favor, selecione a Meta de Gasto e insira um Valor de Lançamento válido (diferente de zero).');
                    return false;
                }

                const success = updateExpenseGoal(categoria, valorAdicionar);

                if (success) {
                    document.getElementById('valorAdicionarDespesa').value = '';
                } else {
                    alert(`Não foi possível encontrar a meta "${categoria}" para atualizar.`);
                    return false;
                }
                
                return true;
            }
        );
    } 
    
    // ======================================================================
    // 📈 LÓGICA DA PÁGINA INVESTMENTS.HTML
    // ======================================================================
    if (currentPage.includes('investments.html')) {
        
        initializeInvestmentPortfolio();
        calculateAndRenderAllInvestments();
        
        // 1. Modal Novo Investimento
        setupModal(
            'modalNovoInvestimento', 
            'novoInvestimentoBtn', 
            'cancelarNovoInvestimento', 
            'confirmarNovoInvestimento',
            () => {
                const nome = document.getElementById('novoInvestimentoNome').value.trim(); 
                const valorInicial = parseFloat(document.getElementById('novoInvestimentoValorInicial').value);
                const cor = document.getElementById('novoInvestimentoCor').value;
                
                if (!nome || isNaN(valorInicial) || valorInicial <= 0) {
                    alert('Por favor, preencha o Nome/Ativo e o Valor Inicial Investido válido (maior que zero).');
                    return false;
                }
                
                if (document.querySelector(`#investmentsGoalsContainer .goal-item[data-title="${nome}"]`)) {
                     alert(`O investimento "${nome}" já existe. Por favor, escolha outro nome.`);
                    return false;
                }

                createNewInvestmentItem(nome, valorInicial, cor); 
                
                document.getElementById('novoInvestimentoNome').value = '';
                document.getElementById('novoInvestimentoValorInicial').value = '';
                
                return true;
            }
        );
        
        // 2. Modal Adicionar Saldo
        setupModal(
            'modalAdicionarValorInvestimento', 
            'adicionarValorInvestimentoBtn', 
            'cancelarAdicionarValorInvestimento', 
            'confirmarAdicionarValorInvestimento',
            () => {
                const investimento = document.getElementById('investimentoSelect').value; 
                const valorAdicionar = parseFloat(document.getElementById('valorAdicionarInvestimento').value);

                if (!investimento || isNaN(valorAdicionar) || valorAdicionar === 0) { 
                    alert('Por favor, selecione o Investimento e insira um Valor a Adicionar válido (diferente de zero).');
                    return false;
                }
                
                const itemElement = document.querySelector(`#investmentsGoalsContainer .goal-item[data-title="${investimento}"]`);
                const currentValue = parseFloat(itemElement.dataset.value) || 0;
                if (currentValue + valorAdicionar < 0) {
                     alert(`A retirada de R$ ${formatCurrency(Math.abs(valorAdicionar))} fará o saldo do investimento ficar negativo. O saldo atual é R$ ${formatCurrency(currentValue)}.`);
                     return false;
                }

                const success = addInvestmentValue(investimento, valorAdicionar);

                if (success) {
                    document.getElementById('valorAdicionarInvestimento').value = '';
                } else {
                    alert(`Não foi possível encontrar o investimento "${investimento}" para atualizar.`);
                    return false;
                }
                
                return true;
            }
        );
    }
});