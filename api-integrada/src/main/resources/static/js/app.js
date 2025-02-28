// Configuração da API
const API_URL = 'http://localhost:8080/api';

// Inicialização da aplicação
document.addEventListener('DOMContentLoaded', function() {
    // Configuração das tabs
    setupTabs();
    
    // Configuração dos formulários
    setupElectionCalculator();
    setupBubbleSort();
    setupFactorial();
    setupMultiples();
    setupVehicles();
    
    // Ativar primeira tab por padrão
    document.querySelector('.nav-link').click();
});

// Configuração das tabs de navegação
function setupTabs() {
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            // Remover classe active de todas as tabs e links
            document.querySelectorAll('.tab-pane').forEach(tab => tab.classList.remove('show', 'active'));
            navLinks.forEach(navLink => navLink.classList.remove('active'));
            
            // Ativar a tab clicada
            const tabId = this.getAttribute('data-tab');
            document.getElementById(tabId).classList.add('show', 'active');
            this.classList.add('active');
        });
    });
}

// ==================== EXERCÍCIO 1: CALCULADORA DE ELEIÇÃO ====================
function setupElectionCalculator() {
    const form = document.getElementById('election-form');
    const resultsDiv = document.getElementById('election-results');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const totalEleitores = parseInt(document.getElementById('total-voters').value);
        const votosValidos = parseInt(document.getElementById('valid-votes').value);
        const votosBrancos = parseInt(document.getElementById('blank-votes').value);
        const votosNulos = parseInt(document.getElementById('null-votes').value);
        
        // Validação básica
        if (votosValidos + votosBrancos + votosNulos > totalEleitores) {
            alert('A soma dos votos não pode ser maior que o total de eleitores!');
            return;
        }
        
        const data = {
            totalEleitores,
            votosValidos,
            votosBrancos,
            votosNulos
        };
        
        fetch(`${API_URL}/calculadora/eleicao`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById('valid-percentage').textContent = data.percentualValidos.toFixed(2) + '%';
            document.getElementById('blank-percentage').textContent = data.percentualBrancos.toFixed(2) + '%';
            document.getElementById('null-percentage').textContent = data.percentualNulos.toFixed(2) + '%';
            
            resultsDiv.classList.remove('d-none');
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao calcular as estatísticas da eleição.');
        });
    });
}

// ==================== EXERCÍCIO 2: BUBBLE SORT ====================
function setupBubbleSort() {
    const form = document.getElementById('bubblesort-form');
    const resultsDiv = document.getElementById('bubblesort-results');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const arrayInput = document.getElementById('array-input').value;
        const arrayValues = arrayInput.split(',').map(item => parseInt(item.trim()));
        
        // Validação básica
        if (arrayValues.some(isNaN)) {
            alert('Por favor, insira apenas números separados por vírgula.');
            return;
        }
        
        fetch(`${API_URL}/ordenacao/bubble-sort`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(arrayValues)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById('original-array').textContent = data.vetorOriginal.join(', ');
            document.getElementById('sorted-array').textContent = data.vetorOrdenado.join(', ');
            
            const stepsContainer = document.getElementById('sort-steps');
            stepsContainer.innerHTML = '';
            
            data.passos.forEach(passo => {
                const stepElement = document.createElement('div');
                stepElement.classList.add('mb-2');
                stepElement.textContent = passo;
                stepsContainer.appendChild(stepElement);
            });
            
            resultsDiv.classList.remove('d-none');
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao ordenar o array.');
        });
    });
}

// ==================== EXERCÍCIO 3: FATORIAL ====================
function setupFactorial() {
    const form = document.getElementById('factorial-form');
    const resultsDiv = document.getElementById('factorial-results');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const numero = parseInt(document.getElementById('factorial-input').value);
        
        // Validação básica
        if (numero < 0 || numero > 100) {
            alert('Por favor, insira um número não-negativo entre 0 e 100.');
            return;
        }
        
        fetch(`${API_URL}/fatorial/${numero}`)
        .then(response => response.json())
        .then(data => {
            if (data.status === 'sucesso') {
                document.getElementById('factorial-number').textContent = `${numero}! = `;
                document.getElementById('factorial-result').textContent = data.fatorial;
                resultsDiv.classList.remove('d-none');
            } else {
                alert(`Erro: ${data.erro}`);
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao calcular o fatorial.');
        });
    });
}

// ==================== EXERCÍCIO 4: MÚLTIPLOS 3 ou 5 ====================
function setupMultiples() {
    const form = document.getElementById('multiples-form');
    const resultsDiv = document.getElementById('multiples-results');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const limite = parseInt(document.getElementById('limit-input').value);
        
        // Validação básica
        if (limite <= 0) {
            alert('Por favor, insira um número positivo.');
            return;
        }
        
        fetch(`${API_URL}/multiplos/${limite}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('multiples-list').textContent = data.multiplos.join(', ');
            document.getElementById('multiples-sum').textContent = data.soma;
            
            resultsDiv.classList.remove('d-none');
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao calcular os múltiplos.');
        });
    });
}

// ==================== EXERCÍCIO 5: CADASTRO DE VEÍCULOS ====================
function setupVehicles() {
    // Referências aos elementos
    const vehiclesTable = document.getElementById('vehicles-table');
    const filterForm = document.getElementById('vehicles-filter-form');
    const vehicleForm = document.getElementById('vehicle-form');
    const newVehicleBtn = document.getElementById('new-vehicle-btn');
    const saveVehicleBtn = document.getElementById('save-vehicle');
    const confirmDeleteBtn = document.getElementById('confirm-delete');
    
    // Bootstrap modals
    const vehicleModal = new bootstrap.Modal(document.getElementById('vehicleModal'));
    const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
    
    // Carregar marcas válidas
    loadBrands();
    
    // Carregar dados iniciais
    loadVehicles();
    loadStatistics();
    
    // Event listeners
    filterForm.addEventListener('submit', function(e) {
        e.preventDefault();
        loadVehicles();
    });
    
    newVehicleBtn.addEventListener('click', function() {
        resetVehicleForm();
        document.getElementById('vehicleModalTitle').textContent = 'Novo Veículo';
        vehicleModal.show();
    });
    
    saveVehicleBtn.addEventListener('click', function() {
        saveVehicle();
    });
    
    confirmDeleteBtn.addEventListener('click', function() {
        const id = document.getElementById('delete-vehicle-id').value;
        deleteVehicle(id);
    });
    
    // Funções auxiliares
    function loadBrands() {
        fetch(`${API_URL}/veiculos/marcas-validas`)
        .then(response => response.json())
        .then(data => {
            const filterBrandSelect = document.getElementById('filter-brand');
            const vehicleBrandSelect = document.getElementById('vehicle-brand');
            
            // Limpar opções existentes
            filterBrandSelect.innerHTML = '<option value="">Todas</option>';
            vehicleBrandSelect.innerHTML = '<option value="">Selecione uma marca</option>';
            
            // Adicionar marcas válidas
            data.forEach(marca => {
                const filterOption = document.createElement('option');
                filterOption.value = marca;
                filterOption.textContent = marca;
                filterBrandSelect.appendChild(filterOption);
                
                const vehicleOption = document.createElement('option');
                vehicleOption.value = marca;
                vehicleOption.textContent = marca;
                vehicleBrandSelect.appendChild(vehicleOption);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar marcas:', error);
        });
    }
    
    function loadVehicles() {
        const marca = document.getElementById('filter-brand').value;
        const ano = document.getElementById('filter-year').value;
        
        let url = `${API_URL}/veiculos`;
        const params = [];
        
        if (marca) params.push(`marca=${marca}`);
        if (ano) params.push(`ano=${ano}`);
        
        if (params.length > 0) {
            url += '?' + params.join('&');
        }
        
        fetch(url)
        .then(response => response.json())
        .then(data => {
            vehiclesTable.innerHTML = '';
            
            data.forEach(vehicle => {
                const row = document.createElement('tr');
                
                row.innerHTML = `
                    <td>${vehicle.id}</td>
                    <td>${vehicle.veiculo}</td>
                    <td>${vehicle.marca}</td>
                    <td>${vehicle.ano}</td>
                    <td>${vehicle.vendido ? '<span class="badge bg-success">Vendido</span>' : '<span class="badge bg-warning text-dark">Disponível</span>'}</td>
                    <td>
                        <button class="btn btn-sm btn-info edit-vehicle" data-id="${vehicle.id}">Editar</button>
                        <button class="btn btn-sm btn-danger delete-vehicle" data-id="${vehicle.id}">Excluir</button>
                    </td>
                `;
                
                vehiclesTable.appendChild(row);
            });
            
            // Adicionar event listeners aos botões de ação
            document.querySelectorAll('.edit-vehicle').forEach(button => {
                button.addEventListener('click', function() {
                    const id = this.getAttribute('data-id');
                    editVehicle(id);
                });
            });
            
            document.querySelectorAll('.delete-vehicle').forEach(button => {
                button.addEventListener('click', function() {
                    const id = this.getAttribute('data-id');
                    showDeleteConfirmation(id);
                });
            });
        })
        .catch(error => {
            console.error('Erro ao carregar veículos:', error);
        });
    }
    
    function loadStatistics() {
        fetch(`${API_URL}/veiculos/estatisticas`)
        .then(response => response.json())
        .then(data => {
            // Informações gerais
            document.getElementById('total-vehicles').textContent = data.totalVeiculos;
            document.getElementById('not-sold-vehicles').textContent = data.naoVendidos;
            document.getElementById('vehicles-this-week').textContent = data.registradosUltimaSemana;
            
            // Distribuição por década
            const decadeContainer = document.getElementById('decade-distribution');
            decadeContainer.innerHTML = '';
            
            for (const [decada, quantidade] of Object.entries(data.distribuicaoPorDecada)) {
                const item = document.createElement('li');
                item.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');
                item.innerHTML = `
                    ${decada}
                    <span class="badge bg-primary rounded-pill">${quantidade}</span>
                `;
                decadeContainer.appendChild(item);
            }
            
            // Distribuição por fabricante
            const brandContainer = document.getElementById('brand-distribution');
            brandContainer.innerHTML = '';
            
            for (const [marca, quantidade] of Object.entries(data.distribuicaoPorFabricante)) {
                const item = document.createElement('li');
                item.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');
                item.innerHTML = `
                    ${marca}
                    <span class="badge bg-primary rounded-pill">${quantidade}</span>
                `;
                brandContainer.appendChild(item);
            }
        })
        .catch(error => {
            console.error('Erro ao carregar estatísticas:', error);
        });
    }
    
    function resetVehicleForm() {
        document.getElementById('vehicle-id').value = '';
        document.getElementById('vehicle-name').value = '';
        document.getElementById('vehicle-brand').value = '';
        document.getElementById('vehicle-year').value = '';
        document.getElementById('vehicle-description').value = '';
        document.getElementById('vehicle-sold').checked = false;
    }
    
    function editVehicle(id) {
        fetch(`${API_URL}/veiculos/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('vehicle-id').value = data.id;
            document.getElementById('vehicle-name').value = data.veiculo;
            document.getElementById('vehicle-brand').value = data.marca;
            document.getElementById('vehicle-year').value = data.ano;
            document.getElementById('vehicle-description').value = data.descricao || '';
            document.getElementById('vehicle-sold').checked = data.vendido;
            
            document.getElementById('vehicleModalTitle').textContent = 'Editar Veículo';
            vehicleModal.show();
        })
        .catch(error => {
            console.error('Erro ao carregar veículo:', error);
            alert('Erro ao carregar os dados do veículo.');
        });
    }
    
    function saveVehicle() {
        const id = document.getElementById('vehicle-id').value;
        const vehicle = {
            veiculo: document.getElementById('vehicle-name').value,
            marca: document.getElementById('vehicle-brand').value,
            ano: parseInt(document.getElementById('vehicle-year').value),
            descricao: document.getElementById('vehicle-description').value,
            vendido: document.getElementById('vehicle-sold').checked
        };
        
        // Validação básica
        if (!vehicle.veiculo || !vehicle.marca || !vehicle.ano) {
            alert('Por favor, preencha todos os campos obrigatórios.');
            return;
        }
        
        const url = id ? `${API_URL}/veiculos/${id}` : `${API_URL}/veiculos`;
        const method = id ? 'PUT' : 'POST';
        
        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(vehicle)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao salvar veículo');
            }
            return response.json();
        })
        .then(() => {
            vehicleModal.hide();
            loadVehicles();
            loadStatistics();
            alert(id ? 'Veículo atualizado com sucesso!' : 'Veículo cadastrado com sucesso!');
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao salvar o veículo.');
        });
    }
    
    function showDeleteConfirmation(id) {
        document.getElementById('delete-vehicle-id').value = id;
        deleteModal.show();
    }
    
    function deleteVehicle(id) {
        fetch(`${API_URL}/veiculos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir veículo');
            }
            
            deleteModal.hide();
            loadVehicles();
            loadStatistics();
            alert('Veículo excluído com sucesso!');
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro ao excluir o veículo.');
        });
    }
}

// Função utilitária para formatação de data
function formatDate(dateString) {
    if (!dateString) return '';
    
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR') + ' ' + date.toLocaleTimeString('pt-BR');
}